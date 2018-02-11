package com.oneuse.dainbow.accounter.dainbowaccounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DainbowAccounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DainbowAccounterApplication.class, args);
	}
}
