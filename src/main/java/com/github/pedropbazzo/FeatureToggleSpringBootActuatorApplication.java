package com.github.pedropbazzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.github.pedropbazzo")
@SpringBootApplication
public class FeatureToggleSpringBootActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleSpringBootActuatorApplication.class, args);
	}

}
