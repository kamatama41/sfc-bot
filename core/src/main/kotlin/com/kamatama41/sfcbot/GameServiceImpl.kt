package com.kamatama41.sfcbot

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat

@Component
class GameServiceImpl(
        private val gameRepository: GameRepository,
        private val twitterService: TwitterService
): GameService {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun tweetRandomly() {
        val count = gameRepository.count()
        if (count == 0L) throw IllegalStateException("Game Repository is empty.")

        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val game = gameRepository.findOne((Math.random() * count).toLong())
        var message = "${game.title}(${game.publisher}) 発売日:${dateFormat.format(game.release)} 価格:${game.price}円"
        message += if (isAvailable(game.wikipediaUrl)) " ${game.wikipediaUrl}" else " ${game.googleSearchUrl}"

        twitterService.tweet(message)
    }

    private fun isAvailable(url: String): Boolean {
        val http = URL(url).openConnection() as HttpURLConnection
        http.requestMethod = "GET"
        http.connect()
        val responseCode = http.responseCode

        logger.debug("URL: $url, ResponseCode:$responseCode")

        // ResponseCode is success or redirect
        return responseCode == 200 || responseCode == 301
    }
}
