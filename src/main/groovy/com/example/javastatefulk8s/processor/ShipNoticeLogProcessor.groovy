package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice
import groovy.util.logging.Slf4j

/**
 * Created by ravipalakodeti on 11/10/17.
 */
@Slf4j
class ShipNoticeLogProcessor {

    static logShipNoticesInBatch(Collection<ShipNotice> shipNoticesCollection) {
        shipNoticesCollection.each {
            log.info("Notice received for asnNumber: ${it.vin} , customer: ${it.customer}, time: ${it.messageTime}")
        }
    }
}
