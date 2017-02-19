package com.kamatama41.sfcbot

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component("cityService")
@Transactional
class CityServiceImpl(
        private val cityRepository: CityRepository
) : CityService {
    override fun getCity(name: String, country: String): City =
            cityRepository.findByNameAndCountryAllIgnoringCase(name, country)
}
