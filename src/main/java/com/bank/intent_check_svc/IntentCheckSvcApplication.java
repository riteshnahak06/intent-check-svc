package com.bank.intent_check_svc;

import com.bank.intent_check_svc.config.ChatbotIntentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ChatbotIntentConfig.class)
public class IntentCheckSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntentCheckSvcApplication.class, args);
	}

}
