package com.example.javastatefulk8s

import org.apache.camel.component.amqp.AMQPComponent
import org.apache.camel.component.amqp.AMQPConnectionDetails
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication {

	static void main(String[] args) {
		SpringApplication.run DemoApplication, args
	}

    @Bean
    AMQPConnectionDetails amqpConnection() {
        return new AMQPConnectionDetails('amqp://0.0.0.0:61616')
    }

    @Bean
    AMQPComponent amqp() {
        return AMQPComponent.amqpComponent("amqp://0.0.0.0:61616")
    }

}
