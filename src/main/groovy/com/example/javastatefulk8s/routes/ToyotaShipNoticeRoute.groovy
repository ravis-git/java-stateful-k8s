package com.example.javastatefulk8s.routes

import com.example.javastatefulk8s.processor.FordAsnProcessor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

/**
 * Created by ravipalakodeti on 11/11/17.
 */
@Component
class ToyotaShipNoticeRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        from('seda:toyotaChannel')
            .bean(FordAsnProcessor.class, 'processShipNotice')
    }
}
