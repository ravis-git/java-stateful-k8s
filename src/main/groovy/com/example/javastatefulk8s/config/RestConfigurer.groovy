package com.example.javastatefulk8s.config

import com.example.javastatefulk8s.services.ShipNoticesService
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

import javax.ws.rs.ApplicationPath

/**
 * Created by ravipalakodeti on 11/20/17.
 */
@Component
@ApplicationPath('ship-notices')
class RestConfigurer extends ResourceConfig {

    RestConfigurer() {
        register(ShipNoticesService.class)
    }
}
