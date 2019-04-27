package com.wisilica.wiseconnect;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * @author Dave Syer
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class WiseconnectTokenStoreTest {

    private String refreshToken;

    private static final String clientId ="spring-security-oauth2-read-write-client";


    private String obtainAccessToken(String clientId, String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);

        Response response = RestAssured.given().auth().
                preemptive().basic(clientId,"spring-security-oauth2-read-write-client-password1234").and().with().params(params).
                when().post("http://localhost:8080/oauth/token");
        refreshToken = response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token");
        return response.jsonPath().getString("DefaultOAuth2AccessToken.access_token");
    }

    private String obtainRefreshToken(String clientId) {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "refresh_token");
        params.put("client_id", clientId);
        params.put("refresh_token", refreshToken);

        Response response = RestAssured.given().auth()
                .preemptive().basic(clientId,"spring-security-oauth2-read-write-client-password1234").and().with().params(params)
                .when().post("http://localhost:8080/oauth/token");

        return response.jsonPath().getString("access_token");
    }

    private void authorizeClient(String clientId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", clientId);
        params.put("scope", "read,write");

        Response response = RestAssured.given().auth().preemptive()
                .basic(clientId,"spring-security-oauth2-read-write-client-password1234").and().with().params(params).
                        when().post("http://localhost:8080/oauth/authorize");
    }

    @Test
    public void givenUser_whenRevokeRefreshToken_thenRefreshTokenInvalidError() {
        String accessToken1 = obtainAccessToken(clientId, "bhaskaran@ndz", "and@1234");
        String accessToken2 = obtainAccessToken(clientId, "bhaskaran@ndz", "and@1234");
        authorizeClient(clientId);

        String accessToken3 = obtainRefreshToken(clientId);
        authorizeClient(clientId);
        Response refreshTokenResponse = RestAssured.given().
                header("Authorization", "Bearer " + accessToken3)
                .get("http://localhost:8080/tokens");
        assertEquals(200, refreshTokenResponse.getStatusCode());

        Response revokeRefreshTokenResponse = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken1)
                .post("http://localhost:8080/tokens/revokeAccessToken/"+refreshToken);
        assertEquals(200, revokeRefreshTokenResponse.getStatusCode());

        String accessToken4 = obtainRefreshToken(clientId);
        authorizeClient(clientId);
        Response refreshTokenResponse2 = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken4)
                .get("http://localhost:8080/tokens");
        assertEquals(401, refreshTokenResponse2.getStatusCode());
    }
}