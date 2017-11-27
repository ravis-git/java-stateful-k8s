package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice
import com.example.javastatefulk8s.model.jpa.ShipNoticeRepository

import javax.inject.Inject
import javax.transaction.Transactional

/**
 * Created by ravipalakodeti on 11/21/17.
 * This is the camel processor that saves the shipping notices into the postgres database
 */
class ShipNoticeSaveProcessor {

    @Inject
    ShipNoticeRepository shipNoticeRepository


    def saveShipNotice(ShipNotice shipNotice) {
        shipNoticeRepository.save(shipNotice)
    }

}
