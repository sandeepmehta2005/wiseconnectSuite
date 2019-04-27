package com.wisilica.wiseconnect;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class WiseconnectPasswordGrantTypeTest {


    private final static String AUTH_SERVER = "http://localhost:8080";

    private String refreshToken;

    private Map<String, String> params;

    private static final String[] EXPECTED_SCOPES = new String[]{"read","write"};

    private String code;

    private String accessToken="";

    private static final String clientId ="spring-security-oauth2-read-write-client";

    @Test
    public void passwordGrantTesting() throws JSONException {

        /** Password Grant type positive scenario with valid user name, password and client_id */
        passwordGrantFlow("spring-security-oauth2-read-write-client", "bhaskaran@ndz", "and@1234");

        /** AccessToken validation valid case*/
        validateAccessToken(clientId,accessToken);

        /** getting new Access_Token using Refresh_Token */
        gettingAccessTokenUsingRefreshToken("spring-security-oauth2-read-write-client", refreshToken);

        /** AccessToken validation in-valid case*/
        invalidAccessToken(clientId,"124435657");

        /**  Invalid password scenario */
        invalidPasswordGrantFlow("spring-security-oauth2-read-write-client", "bhaskaran@ndz", "1234");

        /** Invalid username and password with valid client id */
        invalidPasswordGrantFlow("spring-security-oauth2-read-write-client", "ndz", "1234");

        /** Invalid  client id with valid username and password */
        invalidPasswordGrantFlow("spring-security-oauth2-read-write-client", "bhaskaran@ndz", "and@1234");

    }

    private void passwordGrantFlow(String clientId, String username, String password) throws JSONException {

        params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);

        Response response = RestAssured.given().auth().preemptive().basic(clientId, "spring-security-oauth2-read-write-client-password1234").and().with().params(params).when().post("http://localhost:8080/oauth/token");
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.access_token"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.expires_in"));
        assertEquals("bearer", response.jsonPath().getString("DefaultOAuth2AccessToken.token_type"));

        /** Additional parameters*/
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.apiId"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusCode"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusMessage"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.UserLoginInfo.UserLoginInfo.detailMessage"));
        accessToken= response.jsonPath().getString("access_token");
    }

    private String gettingAccessTokenUsingRefreshToken(String clientId, String refreshToken) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "refresh_token");
        params.put("client_id", clientId);
        params.put("refresh_token", refreshToken);

        Response response = RestAssured.given().auth()
                .preemptive().basic(clientId,"secret").and().with().params(params)
                .when().post("http://localhost:8080/spring-security-oauth-server/oauth/token");
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.access_token"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.expires_in"));
        return response.jsonPath().getString("access_token");
    }

    private void validateAccessToken(String clientId,String accessToken)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", clientId);
        params.put("refresh_token", refreshToken);

        Response response = RestAssured.given().auth()
                .preemptive().basic(clientId,"secret").and().with().params(params)
                .when().post("http://localhost:8080/oauth/check_token?token="+accessToken);

        assertNotNull(response.jsonPath().getString("Map.user_name"));
        assertNotNull(response.jsonPath().getString("Map.client_id"));
        for (String expectedScope : EXPECTED_SCOPES) {
            assertTrue("Scope ["+expectedScope+"] not found!",response.jsonPath().getString("Map.scope").toString().contains(expectedScope));
        }

        /** Additional parameters*/
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.apiId"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusCode"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusMessage"));
        assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.UserLoginInfo.UserLoginInfo.detailMessage"));
    }

    private void invalidPasswordGrantFlow(String clientId, String username, String password) throws JSONException {

        params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);

        Response response = RestAssured.given().auth().preemptive().basic(clientId, "spring-security-oauth2-read-write-client-password1234").and().with().params(params).when().post("http://localhost:8080/oauth/token");
        assertEquals(500, response.getStatusCode());
    }

    private void invalidAccessToken(String clientId,String accessToken)
    {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("refresh_token", refreshToken);

        Response response = RestAssured.given().auth()
                .preemptive().basic(clientId,"secret").and().with().params(params)
                .when().post("http://localhost:8080/oauth/check_token?token="+accessToken);

        assertNotNull(response.jsonPath().getString("Map.user_name"));
        assertNotNull(response.jsonPath().getString("Map.client_id"));

    }


}