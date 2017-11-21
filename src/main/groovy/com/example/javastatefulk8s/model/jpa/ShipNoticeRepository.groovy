package com.example.javastatefulk8s.model.jpa

import com.example.javastatefulk8s.model.ShipNotice
import org.springframework.data.repository.CrudRepository

/**
 * Created by ravipalakodeti on 11/21/17.
 */
interface ShipNoticeRepository extends CrudRepository<ShipNotice, Integer> {
}
