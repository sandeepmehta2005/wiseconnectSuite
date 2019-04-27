package com.wisilica.wiseconnect;

import com.wisilica.wiseconnect.core.oauth2.WiseconnectTokenStore;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class WiseconnectApplicationTests {

	@Autowired
	ServerProperties serverProperties;

	@Autowired
	private WiseconnectTokenStore tokenStore;

	private final static String AUTH_SERVER = "http://localhost:8080";
	private static String REDIRECT_URL = "http://localhost:8080/home";
	private static String CLIENT_ID ="spring-security-oauth2-read-write-client";
	private static String authorizeUrl = AUTH_SERVER + "/oauth/authorize?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
	private static final String[] EXPECTED_SCOPES = new String[]{"read","write"};
	private Map<String, String> params;
	private String code;
	private String refreshToken="";
	private String accessToken="";
	private static String DEFAULT_PASSWORD="and@1234";
	private String user_name="bhaskaran@ndz";

	@Test
	public void oauthFlowTesting() {

		/** Valid username, passowrd and clientId */
		accessToken = authorizationCodeGrantFlow(CLIENT_ID, user_name, DEFAULT_PASSWORD);

		/** AccessToken validation valid case*/
		validateAccessToken(accessToken);

		/** Authorized access token */
		TokenContainValidAuthorization();

		/** getting new Access_Token using Refresh_Token */
		gettingAccessTokenUsingRefreshToken(CLIENT_ID, refreshToken);

		/** AccessToken validation in-valid case*/
		validateAccessToken("12354678");

		/** Authorization grantFlow with state */
		authorizationCodeGrantFlowWithState("clientId", "admin@cms", DEFAULT_PASSWORD);

		/**  Invalid password scenario */
		invalidCodeGrantFlow( user_name, "1234");

		/** Invalid username and password with valid client id */
		invalidCodeGrantFlow("ndz", "1234");

		/** Invalid  client id with valid username and password */
		authorizationCodeGrantFlow("ramdom-client-id", user_name, DEFAULT_PASSWORD);

	}



	private String authorizationCodeGrantFlow(String clientId, String username, String password) {


		Response response = RestAssured.given().formParams("username", username, "password", password).post(AUTH_SERVER + "/login");
		final String cookieValue = response.getCookie("JSESSIONID");

		// get authorization code
		Map<String, String> params2 = new HashMap<>();
		params2.put("user_oauth_approval", "true");
		params2.put("authorize", "Authorize");
		params2.put("scope.read", "true");
		params2.put("scope.write", "true");
		response = RestAssured.given().cookie("JSESSIONID", cookieValue).formParams(params).post(authorizeUrl);
		assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());

		final String location = response.getHeader(HttpHeaders.LOCATION);
		code = location.substring(location.indexOf("code=") + 5);
		assertNotNull(code);

		params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		params.put("client_id", clientId);
		params.put("redirect_uri", REDIRECT_URL);
		params.put("username", username);
		params.put("password",password);

		response = RestAssured.given().auth().basic(clientId, "spring-security-oauth2-read-write-client-password1234").formParams(params).post("http://localhost:8080/oauth/token");
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.access_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.expires_in"));

		/** Additional parameters*/
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.apiId"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusCode"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusMessage"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.UserLoginInfo.UserLoginInfo.detailMessage"));

		for (String expectedScope : EXPECTED_SCOPES) {
			assertTrue("Scope ["+expectedScope+"] not found!",response.jsonPath().getString("DefaultOAuth2AccessToken.scope").toString().contains(expectedScope));
		}
		return response.jsonPath().getString("DefaultOAuth2AccessToken.access_token");

	}

	private void authorizationCodeGrantFlowWithState(String clientId, String username, String password) {


		// user login
		Response response = RestAssured.given().formParams("username", username, "password", password).post(AUTH_SERVER + "/login");
		final String cookieValue = response.getCookie("JSESSIONID");

		// get authorization code
		params	= new HashMap<String, String>();
		params.put("authorize", "Authorize");
		params.put("scope.read", "true");
		params.put("scope.write", "true");
		response = RestAssured.given().cookie("JSESSIONID", cookieValue).formParams(params).post(authorizeUrl+"&state=codeValue");
		assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());

		final String location = response.getHeader(HttpHeaders.LOCATION);
		final String[] redirectURL = location.split("\\?");
		assertEquals(REDIRECT_URL, redirectURL[0]);
		code = location.substring(location.indexOf("code=") + 5);
		String[] state= location.split("state=");
		assertEquals("codeValue", state[1]);
		assertNotNull(code);

		params = new HashMap<String, String>();
		params.put("user_oauth_approval", "true");
		params.put("authorize", "Authorize");
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		params.put("client_id", clientId);
		params.put("redirect_uri", REDIRECT_URL);
		params.put("username", username);
		params.put("password",password);

		response = RestAssured.given().auth().basic(clientId, "spring-security-oauth2-read-write-client-password1234").params(params).cookie("JSESSIONID", cookieValue).post("http://localhost:8080/oauth/token");
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.access_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.expires_in"));

		/** Additional parameters*/
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.apiId"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusCode"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.wiseStatus.wiseStatus.statusMessage"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.UserLoginInfo.UserLoginInfo.detailMessage"));

		for (String expectedScope : EXPECTED_SCOPES) {
			assertTrue("Scope ["+expectedScope+"] not found!",response.jsonPath().getString("DefaultOAuth2AccessToken.scope").toString().contains(expectedScope));
		}

	}


	private String gettingAccessTokenUsingRefreshToken(String clientId, String refreshToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "refresh_token");
		params.put("client_id", clientId);
		params.put("refresh_token", refreshToken);

		Response response = RestAssured.given().auth()
				.preemptive().basic(clientId,"spring-security-oauth2-read-write-client-password1234").and().with().params(params)
				.when().post("http://localhost:8080/spring-security-oauth-server/oauth/token");
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.access_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.refresh_token"));
		assertNotNull(response.jsonPath().getString("DefaultOAuth2AccessToken.expires_in"));
		return response.jsonPath().getString("access_token");
	}

	private void validateAccessToken(String accessToken)
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", CLIENT_ID);
		params.put("refresh_token", refreshToken);

		Response response = RestAssured.given().auth()
				.preemptive().basic(CLIENT_ID,"spring-security-oauth2-read-write-client-password1234").and().with().params(params)
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

	public void TokenContainValidAuthorization() {
		String tokenValue = accessToken;
		OAuth2Authentication auth = tokenStore.readAuthentication(tokenValue);

		assertTrue(auth.isAuthenticated());
	}

	private void invalidCodeGrantFlow(String username, String password) {


		Response response = RestAssured.given().formParams("username", username, "password", password).post(AUTH_SERVER + "/login");
		final String cookieValue = response.getCookie("JSESSIONID");

		// get authorization code
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_oauth_approval", "true");
		params.put("authorize", "Authorize");
		params.put("scope.read", "true");
		params.put("scope.write", "true");
		response = RestAssured.given().cookie("JSESSIONID", cookieValue).formParams(params).post(authorizeUrl);
		assertEquals(500, response.getStatusCode());
	}
}