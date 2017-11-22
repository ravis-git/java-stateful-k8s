package com.example.javastatefulk8s.services

import com.example.javastatefulk8s.model.ShipNoticesBatch
import com.example.javastatefulk8s.model.jpa.ShipNoticeRepository
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Created by ravipalakodeti on 11/20/17.
 */
@Path('')
@Slf4j
@Component
class ShipNoticesService {

    final JsonBuilder jsonBuilder = new JsonBuilder()

    @Inject
    ShipNoticeRepository shipNoticeRepository

    @Produce(uri = 'seda:newAsnChannel')
    ProducerTemplate producerTemplate

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path('health')
    getServiceHealth() {
        return jsonBuilder {
            name 'ship-notices-service'
            status 'UP'
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ingestShipNoticesBatch(ShipNoticesBatch shipNoticesBatch) {

        /*
        * This service is the ingestion point for shipping notice batches. It splits the shipping notices into
        * individual messages and in parallel saves them and pass them to the post processing module. This prevents
        * the need for a scheduler. Additionally, the messages are routed in the order of arrival within a batch
        * to the specific customer channel. This is implemented by the route
        * */

/*        shipNoticesBatch.getShipNotices().each {
            shipNoticeRepository.save(it)
        }*/
        producerTemplate.sendBody(shipNoticesBatch.getShipNotices())

        return jsonBuilder {
            serivce 'ship-notice'
            method 'PUT'
            code 'CREATED'
            status '201'

        }
    }
}
