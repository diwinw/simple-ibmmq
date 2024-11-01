package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

@SpringBootApplication
@EnableJms
public class DemoApplication {

	static final String qName = "DEV.QUEUE.1"; // A queue from the default MQ Developer container config
	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		// Create the JMS Template object to control connections and sessions.
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a single message with a timestamp
		String msg = "Hello Dew! " + new Date();

		// The default SimpleMessageConverter class will be called and turn a String
		// into a JMS TextMessage
		jmsTemplate.convertAndSend(qName, msg);

		status();
	}
	static void status() {
		System.out.println();
		System.out.println("========================================");
		System.out.println("MQ JMS Sample started. Message sent to queue: " + DemoApplication.qName);
		System.out.println("========================================");
	}
}
