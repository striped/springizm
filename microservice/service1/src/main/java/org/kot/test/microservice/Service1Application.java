package org.kot.test.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Dummy Service that demonstrate cloud configuration and auto discovery mechanism.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 23/12/2017 02:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
}

