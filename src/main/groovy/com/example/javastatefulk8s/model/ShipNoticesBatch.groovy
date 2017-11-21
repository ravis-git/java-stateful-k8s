package com.example.javastatefulk8s.model

import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

/**
 * Created by ravipalakodeti on 11/21/17.
 */
class ShipNoticesBatch {

    int transactionId
    Collection<ShipNotice> shipNotices
}

@Builder(builderStrategy = ExternalStrategy, forClass = ShipNoticesBatch, buildMethodName = 'create',
    includes = 'transactionId')
class ShipNoticesBatchBuilder{}