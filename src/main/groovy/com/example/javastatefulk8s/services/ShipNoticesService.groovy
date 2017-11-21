package com.example.javastatefulk8s.services

import com.example.javastatefulk8s.model.ShipNotice
import com.example.javastatefulk8s.model.jpa.ShipNoticeRepository
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j

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
class ShipNoticesService {

    final JsonBuilder jsonBuilder = new JsonBuilder()

    @Inject
    ShipNoticeRepository shipNoticeRepository

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
    ingestShipNotice(ShipNotice shipNotice) {

        shipNoticeRepository.save(shipNotice)
        return jsonBuilder {
            serivce 'ship-notice'
            method 'PUT'
            code 'CREATED'
            status '201'

        }
    }
}
