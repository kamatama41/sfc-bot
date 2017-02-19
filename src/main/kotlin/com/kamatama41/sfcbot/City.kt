package com.kamatama41.sfcbot

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class City(
        @Id @GeneratedValue
        val id: Long,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        val state: String,
        @Column(nullable = false)
        val country: String,
        @Column(nullable = false)
        val map: String
)
