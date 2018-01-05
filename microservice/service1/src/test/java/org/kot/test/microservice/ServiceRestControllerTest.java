package org.kot.test.microservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Service controller unit test.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 04/01/2018 20:56
 */
public class ServiceRestControllerTest {

	@InjectMocks
	private ServiceRestController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(controller, "answer", "test");
	}

	@Test
	public void testService() {
		assertThat(controller.service())
				.isEqualTo("test");
	}

}
