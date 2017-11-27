package com.example.javastatefulk8s.config

import org.apache.camel.CamelContext
import org.apache.camel.component.amqp.AMQPComponent
import org.apache.camel.component.amqp.AMQPConnectionDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ravipalakodeti on 11/21/17.
 */
@Configuration
class CamelConfigurer {

    @Autowired
    CamelContext camelContext
}
