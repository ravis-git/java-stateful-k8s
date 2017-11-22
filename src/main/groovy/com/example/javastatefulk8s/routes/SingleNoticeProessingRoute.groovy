package com.example.javastatefulk8s.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by ravipalakodeti on 11/11/17.
 */
@Component
@Scope(value = "prototype")
class SingleNoticeProessingRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        from('seda:singleAsnChannel')
            .choice()
                .when()
                    .jsonpath('$.[?(@.customer == "FORD")]')
                    .to('seda:fordChannel')
                .when()
                    .jsonpath('$.[?(@.customer == "TOYOTA")]')
                    .to('seda:toyotaChannel')
                .when()
                    .jsonpath('$.[?(@.customer == "CHRYSLER")]')
                    .to('seda:chryslerChannel')
                .when()
                    .jsonpath('$.[?(@.customer == "GMC")]')
                    .to('seda:gmcChannel')
    }
}
