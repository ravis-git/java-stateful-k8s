package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice


/**
 * Created by ravipalakodeti on 11/11/17.
 */
class FordAsnProcessor extends DefaultShipNoticeProcessor {

    def processShipNotice(ShipNotice shipNotice) {

        //perform the default processing. additional customer specific processing can be included here
        postProcessDefaultShipNotice(shipNotice)
    }
}
