package org.kot.test.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Discovery Component test.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 04/01/2018 16:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscoveryServiceTests {

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void testHealth() {
		ResponseEntity<Map<String, String>> entity = cast(rest.getForEntity("/health", Map.class));

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).containsEntry("status", "UP");
	}

	@Test
	public void testApplicationList() {
		ResponseEntity<Map<String, String>> entity = cast(rest.getForEntity("/eureka/apps", Map.class));

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isNotEmpty();
	}

	/**
	 * Small workaround to deliberately violate the checked type casting.
	 * @param x The object to cast
	 * @param <T> The type parameter observed from context to cast to
	 * @return The object casted to requested type
	 */
	@SuppressWarnings("unchecked")
	private static <T> T cast(Object x) {
		return (T) x;
	}
}
