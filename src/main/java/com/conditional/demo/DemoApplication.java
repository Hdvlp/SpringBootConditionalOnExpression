package com.conditional.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.conditional.demo.test.ConditionalOnExpressionAnnotation;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		ConditionalOnExpressionAnnotation.customTest();
	}

}
