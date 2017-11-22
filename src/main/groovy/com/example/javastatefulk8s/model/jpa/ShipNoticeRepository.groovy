package com.example.javastatefulk8s.model.jpa

import com.example.javastatefulk8s.model.ShipNotice
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ravipalakodeti on 11/21/17.
 */
interface ShipNoticeRepository extends JpaRepository<ShipNotice, Integer> {
}
