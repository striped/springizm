package org.kot.test.microservice.test;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @todo Add JavaDoc
 * @created 30/12/2017 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ServiceIT {

	@Autowired
	private EurekaClient eureka;

	@Test
	public void shouldHaveService() {
		final InstanceInfo service1 = eureka.getNextServerFromEureka("service1", false);
		BDDAssertions.then(service1.getStatus()).isEqualTo(InstanceInfo.InstanceStatus.UP);
	}

	@Test
	public void shouldHaveConfig() {
		final InstanceInfo service1 = eureka.getNextServerFromEureka("config", false);
		BDDAssertions.then(service1.getStatus()).isEqualTo(InstanceInfo.InstanceStatus.UP);
	}
}
