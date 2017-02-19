package com.kamatama41.sfcbot

import org.springframework.data.repository.Repository

interface SoftwareRepository : Repository<Software, Long> {
    fun findAll(): List<Software>
}
