package com.axonbank.usercmdapi;

import com.axonbank.usercoreapi.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class UserCmdApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCmdApiApplication.class, args);
	}

}
