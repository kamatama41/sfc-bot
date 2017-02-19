package com.kamatama41.sfcbot

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface CityRepository : Repository<City, Long> {
    fun findAll(pageable: Pageable): Page<City>

    fun findByNameContainingAndCountryContainingAllIgnoringCase(
            name: String, country: String, pageable: Pageable
    ): Page<City>

    fun findByNameAndCountryAllIgnoringCase(name: String, country: String): City
}
