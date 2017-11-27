package com.example.javastatefulk8s.routes

import com.example.javastatefulk8s.processor.ShipNoticeSaveProcessor
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.amqp.AMQPComponent

//import org.apache.camel.component.amqp.AMQPComponent
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.inject.Inject

/**
 * Created by ravipalakodeti on 11/9/17.
 */
@Component
class ShipNoticeBatchIngestRoute extends RouteBuilder {

    @Inject
    CamelContext camelContext


/*
    @PostConstruct
    def init() {
        AMQPComponent amqp = AMQPComponent.amqpComponent("amqp://amq:61616?wireFormt.maxFrameSize=1200")
        camelContext.addComponent('amqp', amqp)
    }
*/

    @Override
    void configure() throws Exception {
        from('seda:newAsnChannel')
            .routeId('batch-asn-route')
            // the route is processed in streaming mode and does not wait for the entire batch to complete
            .streamCaching()
            .split(body())
                .bean(ShipNoticeSaveProcessor.class, 'saveShipNotice')
                .to('seda:splitOnAsn')
//                    .to('amqp:queue:oneAsn')
    }
}
