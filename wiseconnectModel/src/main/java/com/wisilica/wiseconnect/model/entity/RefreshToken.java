/**
 *  WiseConnect Refresh Token Constants
 *
 *  @author Bhaskaran
 *  @Date  25-Mar-2019
 *
 * Description : entity class for Refresh Token ignite cache
 *
 */

package com.wisilica.wiseconnect.model.entity;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class RefreshToken {

    public RefreshToken(){}

    public RefreshToken(String tokenId, byte[] token, byte[] authentication){
        super();
        this.tokenId=tokenId;
        this.token=token;
        this.authentication=authentication;
    }

    @QuerySqlField(index = true)
    private String tokenId;

    @QuerySqlField(index = true)
    private byte[] token;

    @QuerySqlField(index = true)
    private byte[] authentication;


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

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override public String toString() {
        return "AccessToken [id=" + tokenId +
                ", token=" + token +
                ", authentication=" + authentication+']';
    }
}
