package org.kot.test.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that demonstrate configuration actually happen.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 23/12/2017 02:57
 */
@RestController
class ServiceRestController {

	@Value("${service.answer}")
	private String answer;

	@RequestMapping("/service")
	public String service() {
		return answer;
	}
}
