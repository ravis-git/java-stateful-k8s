package com.example.javastatefulk8s.model

import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by ravipalakodeti on 11/20/17.
 */
@Entity
@Table(name = "ship_notice_req")
class ShipNotice {

    @Id
    int vin
    String customer

    @Column(name = 'message_origin_time')
    String timeOfMessageOrigin

}

@Builder(builderStrategy = ExternalStrategy, forClass = ShipNotice,
    buildMethodName = 'create', includes = 'vin, customer, timeOfMessageOrigin')
class ShipNoticeBuilder{}
