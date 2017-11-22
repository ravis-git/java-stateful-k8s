package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice
import groovy.util.logging.Slf4j

import java.time.Duration
import java.time.Instant

/**
 * Created by ravipalakodeti on 11/11/17.
 *
 *  This is the default vehicle processor. This class is then overloaded to add business logic for specific makes
 */
@Slf4j
class DefaultShipNoticeProcessor {

    static processDefaultShipNotice(ShipNotice shipNotice) {

        log.info("make: ${shipNotice.customer}, ASN number: ${shipNotice.vin}, msgTime: ${shipNotice.messageTime}" +
                " Duration: ${Duration.between(Instant.parse(shipNotice.getMessageTime()), Instant.now()).toMillis()}")

    }
}
