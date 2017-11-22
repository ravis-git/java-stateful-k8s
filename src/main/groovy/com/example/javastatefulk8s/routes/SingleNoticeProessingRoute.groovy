package com.example.javastatefulk8s.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

/**
 * Created by ravipalakodeti on 11/11/17.
 */
@Component
class SingleNoticeProessingRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        from('seda:singleAsnChannel')
            .choice()
                .when()
                    .jsonpath('$.[?(@.customer == "FORD")]')
                .to('seda:fordChannel')
    }
}
