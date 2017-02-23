package com.kamatama41.sfcbot

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

interface TwitterService {
    fun tweet(message: String) {
        LoggerFactory.getLogger(javaClass).info("Tweet: $message")
    }

    @Component
    class DefaultTwitterService : TwitterService
}
