package com.example.javastatefulk8s.routes

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.config.StreamResequencerConfig
import org.springframework.stereotype.Component

/**
 * Created by ravipalakodeti on 11/11/17.
 */
@Component
class SingleNoticeProessingRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
//        from('amqp:queue:oneAsn')
          from('seda:splitOnAsn')
            .routeId('single-asn-route')
            .resequence().jsonpath('$.vin').stream(new StreamResequencerConfig(5000,4000L))
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
