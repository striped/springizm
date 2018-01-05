package org.kot.test.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Dummy Service Component test.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 23/12/2017 02:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
		"eureka.client.enabled=false",
		"spring.cloud.config.enabled=false",
		"spring.cloud.config.discovery.enabled=false",
		"service.answer=test"
})
@AutoConfigureMockMvc
public class Service1Test {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testHealth() throws Exception {
		mvc.perform(get("/health").contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().is2xxSuccessful())
		   .andExpect(jsonPath("status", is("UP")));
	}

	@Test
	public void testService() throws Exception {
		mvc.perform(get("/service").contentType(MediaType.TEXT_PLAIN))
		   .andExpect(status().is2xxSuccessful())
		   .andExpect(content().string("test"));
	}
}
