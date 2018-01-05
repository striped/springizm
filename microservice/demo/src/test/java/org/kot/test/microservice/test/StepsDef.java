package org.kot.test.microservice.test;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Cucumber steps definitions.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 02/01/2018 22:38
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StepsDef {

	@Autowired
	private RestTemplate restTemplate;

	private String url;

	private HttpMethod method;

	private HashMap<String, Object> variables;

	@When("^the client calls ([/\\w]+/*)$")
	public void get(String url) {
		this.method = HttpMethod.GET;
		this.url = url;
		this.variables = new HashMap<>();
	}

	@Then("^response status code is (\\d+)$")
	public void responseIs(int statusCode) {
		restTemplate.execute(url, method, request -> {}, (ResponseExtractor<Void>)response -> {
			assertThat(response.getRawStatusCode(), is(statusCode));
			return null;
		}, variables);
	}

	@Then("^response status code is (\\d+) and it says \"(.+)\"$")
	public void responseIs(int statusCode, String body) {
		restTemplate.execute(url, method, request -> {}, (ResponseExtractor<Void>)response -> {
			assertThat(response.getRawStatusCode(), is(statusCode));
			assertThat(new StringHttpMessageConverter().read(String.class, response), is(body));
			return null;
		}, variables);
	}

	@Then("^response failed with status code (\\d+)$")
	public void failureIs(int statusCode) {
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(final ClientHttpResponse response) throws IOException {
				assertThat(response.getRawStatusCode(), is(statusCode));
				final String actual = new StringHttpMessageConverter().read(String.class, response);
			}
		});
		restTemplate.execute(url, method, request -> {}, (ResponseExtractor<Void>)response -> {
			assertThat("Unexpected response", restTemplate.getErrorHandler().hasError(response), is(true));
			return null;
		}, variables);
	}

	@Then("^response failed with status code (\\d+) and message \"(.+)\"$")
	public void failureIs(int statusCode, String message) {
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(final ClientHttpResponse response) throws IOException {
				assertThat(response.getRawStatusCode(), is(statusCode));
				final String actual = new StringHttpMessageConverter().read(String.class, response);
				assertThat(actual, containsString(message));
			}
		});
		restTemplate.execute(url, method, request -> {}, (ResponseExtractor<Void>)response -> {
			assertThat("Unexpected response", restTemplate.getErrorHandler().hasError(response), is(true));
			return null;
		}, variables);
	}
}
