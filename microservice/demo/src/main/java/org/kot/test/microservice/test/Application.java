package org.kot.test.microservice.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

/**
 * System client demo.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 03/01/2018 20:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).web(false).run(args);
		final String response = ctx.getBean(RestTemplate.class).getForObject("/service", String.class);
		System.out.println(response);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder, DiscoveryClient discovery) {
		DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
		discovery.getInstances("service1")
		         .stream().findFirst().ifPresent(service -> uriTemplateHandler.setBaseUrl(service.getUri().toString()));
		return builder.uriTemplateHandler(uriTemplateHandler).build();
	}
}
