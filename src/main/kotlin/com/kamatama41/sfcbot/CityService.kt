package com.kamatama41.sfcbot

interface CityService {
    fun getCity(name: String, country: String): City
}
