package com.kamatama41.sfcbot

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SoftwareController(private val softwareService: SoftwareService) {
    @GetMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    fun index(): Iterable<Software> = softwareService.findAll()
}
