package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice
import com.example.javastatefulk8s.model.jpa.ShipNoticeRepository
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset

/**
 * Created by ravipalakodeti on 11/11/17.
 *
 *  This is the default vehicle processor. This class is then overloaded to add business logic for specific makes
 *
 *  This class logs the time and duration for processing any ship notice
 */
@Slf4j
@Component
class DefaultShipNoticeProcessor {

    @Inject
    ShipNoticeRepository shipNoticeRepository

    def postProcessDefaultShipNotice(ShipNotice shipNotice) {

        log.info(
          "make: ${shipNotice.customer}, " +
          "ASN number: ${shipNotice.vin}, " +
          "msgTime: ${shipNotice.getTimeOfMessageOrigin()}" +
          " Duration: ${Duration.between(Instant.parse(shipNotice.getTimeOfMessageOrigin()), Instant.now()).toMillis()}")

        shipNotice.setTimeMessageProcessed(Instant.now().atOffset(ZoneOffset.UTC).toString())
        shipNotice.setDuration(
            Duration.between(Instant.parse(shipNotice.getTimeOfMessageOrigin()), Instant.now()).toMillis()
        )

        shipNoticeRepository.save(shipNotice)
    }
}
