package cgg.example.ecom_order_service.client.config;

import java.time.Duration;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cgg.example.ecom_order_service.exceptions.CustomErrorDecoder;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

@Configuration
public class InventoryFeignConfig {

	@Bean
	public Logger.Level loggerInventory(){
//		NONE-log only the request method and url and the response status code and execution time
//		BASIC- log the basic information along with request and response headers
//		FULL- log  the headers,body,and metadata for both requests and responses
		return Logger.Level.FULL;
	}
	
	@Bean
	public Request.Options options(){
//		return new Request.Options(3000,5000);
		return new Request.Options(Duration.ofMillis(3000),Duration.ofMillis(5000),true);
	}
	
	@Bean
	public Retryer retryer() {
		return new Retryer.Default(1L,2L,5);
	}
		
	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate->{
			requestTemplate.header("X-Correlation-Id", UUID.randomUUID().toString());
		};
	}
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
	
	@Bean
	public Encoder encoder() {
		return new CustomFeignEncoder();
	}
	
	
	
	
	
	
	
}

