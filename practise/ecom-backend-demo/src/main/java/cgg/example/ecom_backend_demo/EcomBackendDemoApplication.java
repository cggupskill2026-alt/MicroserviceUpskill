package cgg.example.ecom_backend_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcomBackendDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomBackendDemoApplication.class, args);
	}

}
