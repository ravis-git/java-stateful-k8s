package com.example.javastatefulk8s.config

import org.apache.camel.CamelContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

/**
 * Created by ravipalakodeti on 11/21/17.
 */
@Configuration
class CamelConfigurer {

    @Autowired
    CamelContext camelContext
}
