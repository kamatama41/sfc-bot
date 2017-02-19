package com.kamatama41.sfcbot

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SoftwareServiceImpl(
        private val softwareRepository: SoftwareRepository
) : SoftwareService {
    override fun findAll(): List<Software> = softwareRepository.findAll()
}
