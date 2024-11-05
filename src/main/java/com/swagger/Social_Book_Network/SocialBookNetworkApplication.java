package com.swagger.Social_Book_Network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialBookNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialBookNetworkApplication.class, args);
	}

}
