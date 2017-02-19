package com.kamatama41.sfcbot

import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CityController(private val cityService: CityService) {
    @GetMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    fun helloWorld(): String {
        return cityService.getCity("Bath", "UK").name
    }
}
