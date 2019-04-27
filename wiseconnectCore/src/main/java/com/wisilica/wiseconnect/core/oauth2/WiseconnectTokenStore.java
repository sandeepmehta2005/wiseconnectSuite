
package com.wisilica.wiseconnect.core.oauth2;

/**
 *  WiseConnect Token Store
 *
 *  @author Bhaskaran
 *  @Date  25-Mar-2019
 *
 * Description : Extending TokenStore class for storing values into ignite cache
 *
 */

import com.wisilica.wiseconnect.model.entity.AccessToken;
import com.wisilica.wiseconnect.model.entity.RefreshToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WiseconnectTokenStore implements TokenStore {
    private String selectAccessTokenFromAuthenticationSql = DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT;
    private static final String DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT = "select token_id, token from oauth_access_token where authentication_id = ?";
    private static final Log LOG = LogFactory.getLog(WiseconnectTokenStore.class);
    private String insertAccessTokenSql = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token, phone_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private String selectAccessTokensFromUserNameAndClientIdSql = "select token_id, token from oauth_access_token where user_name=? and phone_id = ? and client_id = ?";
    private String selectAccessTokensFromUserNameSql = "select token_id, token from oauth_access_token where user_name = ?";
    private String selectAccessTokensFromClientIdSql = "select token_id, token from oauth_access_token where client_id = ?";
    private String deleteAccessTokenSql = "delete from oauth_access_token where token_id = ?";
    private String insertRefreshTokenSql = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";
    private String deleteRefreshTokenSql = "delete from oauth_refresh_token where token_id = ?";
    private String deleteAccessTokenFromRefreshTokenSql = "delete from oauth_access_token where refresh_token = ?";
    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    private final JdbcTemplate jdbcTemplate;
    private String phoneId="1";

    public WiseconnectTokenStore(DataSource dataSource) {
        Assert.notNull(dataSource, "DataSource required");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public IgniteCache refreshTokenMethod(){
        Ignite ignite = Ignition.ignite();
        CacheConfiguration<String, RefreshToken> cacheCfg = new CacheConfiguration<>("refreshTokenCache");
        cacheCfg.setIndexedTypes(String.class, RefreshToken.class);
        IgniteCache<String, RefreshToken> refreshCacheFields = ignite.getOrCreateCache(cacheCfg);
        return refreshCacheFields;
    }
    public IgniteCache accessTokenMethod(){
        Ignite ignite = Ignition.ignite();
        CacheConfiguration<String, AccessToken> cacheCfg = new CacheConfiguration<>("accessTokenCache");
        cacheCfg.setIndexedTypes(String.class, AccessToken.class);
        IgniteCache<String, AccessToken> accessTokenCacheFields = ignite.getOrCreateCache(cacheCfg);
        return accessTokenCacheFields;
    }

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;
        String key = this.authenticationKeyGenerator.extractKey(authentication);

        try {
            accessToken = (OAuth2AccessToken)this.jdbcTemplate.queryForObject(this.selectAccessTokenFromAuthenticationSql, new RowMapper<OAuth2AccessToken>() {
                public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return WiseconnectTokenStore.this.deserializeAccessToken(rs.getBytes(2));
                }
            }, new Object[]{key});
        } catch (EmptyResultDataAccessException var5) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to find access token for authentication " + authentication);
            }
        } catch (IllegalArgumentException var6) {
            LOG.error("Could not extract access token for authentication " + authentication, var6);
        }

        if (accessToken != null && !key.equals(this.authenticationKeyGenerator.extractKey(this.readAuthentication(accessToken.getValue())))) {
            this.removeAccessToken(accessToken.getValue());
            this.storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }
        AccessToken accessTokenVal =new AccessToken();
        accessTokenVal.setClientId(authentication.getOAuth2Request().getClientId());
        accessTokenVal.setToken(this.serializeAccessToken(token));
        accessTokenVal.setTokenId(this.extractTokenKey(token.getValue()));
        accessTokenVal.setUserName(authentication.isClientOnly() ? null : authentication.getName());
        accessTokenVal.setAuthendicationId(this.authenticationKeyGenerator.extractKey(authentication));
        accessTokenVal.setAuthentication(this.serializeAuthentication(authentication));
        accessTokenVal.setRefreshToken(refreshToken);
        IgniteCache<String, AccessToken> cacheCfg = accessTokenMethod();
        cacheCfg.put(token.getValue().toString(),accessTokenVal);
        this.jdbcTemplate.update(this.insertAccessTokenSql, new Object[]{this.extractTokenKey(token.getValue()), new SqlLobValue(this.serializeAccessToken(token)), this.authenticationKeyGenerator.extractKey(authentication), authentication.isClientOnly() ? null : authentication.getName(), authentication.getOAuth2Request().getClientId(), new SqlLobValue(this.serializeAuthentication(authentication)), this.extractTokenKey(refreshToken),"1"}, new int[]{12, 2004, 12, 12, 12, 2004, 12, 12});
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;
        try {
            byte[] token = new byte[0];
            IgniteCache<String, AccessToken> cacheCfg = accessTokenMethod();
            if(cacheCfg.containsKey(tokenValue)){
                token=cacheCfg.get(tokenValue).getToken();
            }
            accessToken = (OAuth2AccessToken)  WiseconnectTokenStore.this.deserializeAccessToken(token);
        } catch (EmptyResultDataAccessException var4)  {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + tokenValue);
            }
        } catch (IllegalArgumentException var5) {
            LOG.warn("Failed to deserialize access token for " + tokenValue, var5);
            this.removeAccessToken(tokenValue);
        }
        return accessToken;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        this.removeAccessToken(token.getValue());
    }

    public void removeAccessToken(String tokenValue) {
        this.jdbcTemplate.update(this.deleteAccessTokenSql, new Object[]{this.extractTokenKey(tokenValue)});
        IgniteCache<String, AccessToken> cacheCfg = accessTokenMethod();
        if(cacheCfg.containsKey(tokenValue)) {
            cacheCfg.remove(tokenValue);
        }
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return this.readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) throws EmptyResultDataAccessException {
        OAuth2Authentication authentication = null;

        try {
            byte[] tokenAuth = new byte[0];
            IgniteCache<String, AccessToken> cacheCfg = accessTokenMethod();
            if(cacheCfg.containsKey(token)){
                tokenAuth=cacheCfg.get(token).getAuthentication();
            }
            authentication = (OAuth2Authentication) WiseconnectTokenStore.this.deserializeAuthentication(tokenAuth);
        } catch (EmptyResultDataAccessException var4) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + token);
            }
        } catch (IllegalArgumentException var5) {
            LOG.warn("Failed to deserialize authentication for " + token, var5);
            this.removeAccessToken(token);
        }

        return authentication;
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        RefreshToken refreshTokenVal =new RefreshToken();
        refreshTokenVal.setToken(this.serializeRefreshToken(refreshToken));
        refreshTokenVal.setTokenId(this.extractTokenKey(refreshToken.getValue()));
        refreshTokenVal.setAuthentication(this.serializeAuthentication(authentication));
        IgniteCache<String,RefreshToken> refreshTkn =refreshTokenMethod();
        refreshTkn.put(refreshToken.getValue(),refreshTokenVal);
        this.jdbcTemplate.update(this.insertRefreshTokenSql, new Object[]{this.extractTokenKey(refreshToken.getValue()), new SqlLobValue(this.serializeRefreshToken(refreshToken)), new SqlLobValue(this.serializeAuthentication(authentication))}, new int[]{12, 2004, 2004});
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String token) {
        OAuth2RefreshToken refreshToken = null;

        try {
            byte[] tokenVal = new byte[0];
            IgniteCache<String,RefreshToken> refresh =refreshTokenMethod();
            if(refresh.containsKey(token)){
                tokenVal=refresh.get(token).getToken();
            }
            return WiseconnectTokenStore.this.deserializeRefreshToken(tokenVal);
        } catch (EmptyResultDataAccessException var4) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find refresh token for token " + token);
            }
        } catch (IllegalArgumentException var5) {
            LOG.warn("Failed to deserialize refresh token for token " + token, var5);
            this.removeRefreshToken(token);
        }

        return refreshToken;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        this.removeRefreshToken(token.getValue());

    }

    public void removeRefreshToken(String token) {
        this.jdbcTemplate.update(this.deleteRefreshTokenSql, new Object[]{this.extractTokenKey(token)});
        IgniteCache<String,RefreshToken> refreshTkn =refreshTokenMethod();
        if(refreshTkn.containsKey(token)) {
            refreshTkn.remove(token);
        }
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return this.readAuthenticationForRefreshToken(token.getValue());
    }

    public OAuth2Authentication readAuthenticationForRefreshToken(String value) {
        OAuth2Authentication authentication = null;
        try {
            byte[] tokenAuth = new byte[0];
            IgniteCache<String,RefreshToken> refresh =refreshTokenMethod();
            if(refresh.containsKey(value)){
                tokenAuth=refresh.get(value).getAuthentication();
            }
            authentication = (OAuth2Authentication) WiseconnectTokenStore.this.deserializeAuthentication(tokenAuth);
        } catch (EmptyResultDataAccessException var4) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + value);
            }
        } catch (IllegalArgumentException var5) {
            LOG.warn("Failed to deserialize access token for " + value, var5);
            this.removeRefreshToken(value);
        }
        return authentication;
    }
    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        this.removeAccessTokenUsingRefreshToken(refreshToken.getValue());
    }

    public void removeAccessTokenUsingRefreshToken(String refreshToken) {
        this.jdbcTemplate.update(this.deleteAccessTokenFromRefreshTokenSql, new Object[]{this.extractTokenKey(refreshToken)}, new int[]{12});
        SqlQuery<AffinityKey<String>, AccessToken> qry1 =  new SqlQuery<>(AccessToken.class, "delete * from AccessToken where refreshToken= ?");
        qry1.setArgs(refreshToken);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        Object accessTokens1 = new ArrayList();

        try {
            accessTokens1 = this.jdbcTemplate.query(this.selectAccessTokensFromClientIdSql, new WiseconnectTokenStore.SafeAccessTokenRowMapper(), new Object[]{clientId});
        } catch (EmptyResultDataAccessException var4) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for clientId " + clientId);
            }
        }

        List<OAuth2AccessToken> accessTokens = this.removeNulls((List)accessTokens1);
        return accessTokens;
    }

    public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
        Object accessTokens1 = new ArrayList();

        try {
            accessTokens1 = this.jdbcTemplate.query(this.selectAccessTokensFromUserNameSql, new WiseconnectTokenStore.SafeAccessTokenRowMapper(), new Object[]{userName});
        } catch (EmptyResultDataAccessException var4) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for userName " + userName);
            }
        }

        List<OAuth2AccessToken> accessTokens = this.removeNulls((List)accessTokens1);
        return accessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        Object accessTokens1 = new ArrayList();
        List<OAuth2AccessToken> accessTokens = null;
        try {
            accessTokens1 = this.jdbcTemplate.query(this.selectAccessTokensFromUserNameAndClientIdSql, new WiseconnectTokenStore.SafeAccessTokenRowMapper(), new Object[]{userName, phoneId, clientId});
          //  phoneId = this.jdbcTemplate.query(this.selectPhoneIdSql, new WiseconnectTokenStore.SafeAccessTokenRowMapper(), new Object[]{phoneId});
        } catch (EmptyResultDataAccessException var5) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for clientId " + clientId + " and userName " + userName);
            }
        }
           accessTokens = this.removeNulls((List) accessTokens1);
        return accessTokens;
    }

    private List<OAuth2AccessToken> removeNulls(List<OAuth2AccessToken> accessTokens) {
        List<OAuth2AccessToken> tokens = new ArrayList();
        Iterator var3 = accessTokens.iterator();
        while(var3.hasNext()) {
            OAuth2AccessToken token = (OAuth2AccessToken)var3.next();
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    protected String extractTokenKey(String value) {
        if (value == null) {
            return null;
        } else {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var5) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
            }

            try {
                byte[] bytes = digest.digest(value.getBytes("UTF-8"));
                return String.format("%032x", new BigInteger(1, bytes));
            } catch (UnsupportedEncodingException var4) {
                throw new IllegalStateException("UbTF-8 encoding not available.  Fatal (should be in the JDK).");
            }
        }
    }

    protected byte[] serializeAccessToken(OAuth2AccessToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
        return SerializationUtils.serialize(authentication);
    }

    protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
        return (OAuth2AccessToken) SerializationUtils.deserialize(token);
    }

    protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
        return (OAuth2RefreshToken) SerializationUtils.deserialize(token);
    }

    protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
        return (OAuth2Authentication) SerializationUtils.deserialize(authentication);
    }

    public void setInsertAccessTokenSql(String insertAccessTokenSql) {
        this.insertAccessTokenSql = insertAccessTokenSql;
    }

    public void setDeleteAccessTokenSql(String deleteAccessTokenSql) {
        this.deleteAccessTokenSql = deleteAccessTokenSql;
    }

    public void setInsertRefreshTokenSql(String insertRefreshTokenSql) {
        this.insertRefreshTokenSql = insertRefreshTokenSql;
    }

    public void setDeleteRefreshTokenSql(String deleteRefreshTokenSql) {
        this.deleteRefreshTokenSql = deleteRefreshTokenSql;
    }

      public void setDeleteAccessTokenFromRefreshTokenSql(String deleteAccessTokenFromRefreshTokenSql) {
        this.deleteAccessTokenFromRefreshTokenSql = deleteAccessTokenFromRefreshTokenSql;
    }

    public void setSelectAccessTokensFromUserNameSql(String selectAccessTokensFromUserNameSql) {
        this.selectAccessTokensFromUserNameSql = selectAccessTokensFromUserNameSql;
    }

    public void setSelectAccessTokensFromUserNameAndClientIdSql(String selectAccessTokensFromUserNameAndClientIdSql) {
        this.selectAccessTokensFromUserNameAndClientIdSql = selectAccessTokensFromUserNameAndClientIdSql;
    }

    public void setSelectAccessTokensFromClientIdSql(String selectAccessTokensFromClientIdSql) {
        this.selectAccessTokensFromClientIdSql = selectAccessTokensFromClientIdSql;
    }

    private final class SafeAccessTokenRowMapper implements RowMapper<OAuth2AccessToken> {
        private SafeAccessTokenRowMapper() {}

        @Override
        public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return WiseconnectTokenStore.this.deserializeAccessToken(rs.getBytes(2));
            } catch (IllegalArgumentException var5) {
                String token = rs.getString(1);
                WiseconnectTokenStore.this.jdbcTemplate.update(WiseconnectTokenStore.this.deleteAccessTokenSql, new Object[]{token});
                SqlQuery<AffinityKey<String>, AccessToken> qry1 =  new SqlQuery<>(AccessToken.class, "delete * from AccessToken where token= ?");
                qry1.setArgs(token);
                return null;
            }
        }
    }
}