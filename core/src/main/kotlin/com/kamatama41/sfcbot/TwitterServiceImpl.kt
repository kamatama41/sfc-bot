package com.kamatama41.sfcbot

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

class TwitterServiceImpl(builder: Builder) : TwitterService {
    private val logger = LoggerFactory.getLogger(javaClass)!!
    private val twitter: Twitter by lazy {
        val singleton = TwitterFactory.getSingleton()
        singleton.setOAuthConsumer(builder.consumerKey, builder.consumerSecret)
        singleton.oAuthAccessToken = AccessToken(builder.token, builder.tokenSecret)
        singleton
    }

    override fun tweet(message: String) {
        twitter.updateStatus(message)
        logger.info("Tweet: $message")
    }

    @Configuration
    @ConfigurationProperties(prefix = "twitter")
    class Builder {
        lateinit var consumerKey: String
        lateinit var consumerSecret: String
        lateinit var token: String
        lateinit var tokenSecret: String

        @Bean @Primary
        @ConditionalOnProperty(
                prefix = "twitter",
                name = arrayOf("consumer_key", "consumer_secret", "token", "token_secret")
        ) fun build() = TwitterServiceImpl(this)
    }
}
