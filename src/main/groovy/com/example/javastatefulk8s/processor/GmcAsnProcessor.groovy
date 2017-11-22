package com.example.javastatefulk8s.processor

import com.example.javastatefulk8s.model.ShipNotice


/**
 * Created by ravipalakodeti on 11/11/17.
 */
class GmcAsnProcessor extends DefaultShipNoticeProcessor {

    // making this method static may give better overall result as the jvm does not have multiple instances.
        // -- This may be a future concern when running multiple instances of the service
    def processShipNotice(ShipNotice shipNotice) {

        //perform the default processing. additional customer specific processing can be included here
        postProcessDefaultShipNotice(shipNotice)
    }
}
