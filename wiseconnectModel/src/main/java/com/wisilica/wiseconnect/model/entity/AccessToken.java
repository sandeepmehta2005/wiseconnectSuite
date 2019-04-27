/**
 *  WiseConnect AccessToken Constants
 *
 *  @author Bhaskaran
 *  @Date  20-Mar-2019
 *
 * Description : AccessToken ignite cache entity class
 *
 */

package com.wisilica.wiseconnect.model.entity;

import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class AccessToken {

    @QuerySqlField(index = true)
    private String tokenId;

    @QuerySqlField(index = true)
    private byte[] token;

    @QuerySqlField(index = true)
    private String authendicationId;

    @QuerySqlField(index = true)
    private String userName;

    @QuerySqlField(index = true)
    private String clientId;

    @QuerySqlField(index = true)
    private byte[] authentication;

    @QuerySqlField(index = true)
    private String refreshToken;

    public AccessToken(){}

     public AccessToken(String tokenId, byte[] token, String authendicationId, String userName,String clientId, byte[]  authentication, String refreshToken) {
     super();
     this.tokenId=tokenId;
     this.token=token;
     this.authendicationId=authendicationId;
     this.userName=userName;
     this.clientId=clientId;
     this.authentication=authentication;
     this.refreshToken=refreshToken;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getAuthendicationId() {
        return authendicationId;
    }

    public void setAuthendicationId(String authendicationId) {
        this.authendicationId = authendicationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override public String toString() {
        return "AccessToken [id=" + tokenId +
                ", token=" + token +
                ", authendicationId=" + authendicationId +
                ", userName=" + userName +
                ", clientId=" + clientId +
                ", authentication=" + authentication +
                ", refreshToken=" + refreshToken + ']';
    }
}
