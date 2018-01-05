package org.kot.test.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Configuration storage application.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 29/12/2017 13:15
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class GitConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitConfigServiceApplication.class, args);
	}
}
