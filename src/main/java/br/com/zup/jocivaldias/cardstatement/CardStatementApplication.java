package br.com.zup.jocivaldias.cardstatement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class CardStatementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardStatementApplication.class, args);
	}

}
