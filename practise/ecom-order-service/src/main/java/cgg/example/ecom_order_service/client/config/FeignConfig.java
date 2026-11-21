package cgg.example.ecom_order_service.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {

	@Bean
	public Logger.Level logger(){
//		NONE-log only the request method and url and the response status code and execution time
//		BASIC- log the basic information along with request and response headers
//		FULL- log  the headers,body,and metadata for both requests and responses
		return Logger.Level.FULL;
	}
}
