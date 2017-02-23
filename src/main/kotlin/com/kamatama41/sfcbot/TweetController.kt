package com.kamatama41.sfcbot

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat

@RestController
class TweetController(
        private val gameRepository: GameRepository,
        private val twitterService: TwitterService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/tweet", method = arrayOf(RequestMethod.GET))
//    @RequestMapping("/tweet", method = arrayOf(RequestMethod.POST))
    fun tweet(): String {
        val count = gameRepository.count()
        if (count == 0L) throw IllegalStateException("Game Repository is empty.")

        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val game = gameRepository.findOne((Math.random() * count).toLong())
        var message = "${game.title}(${game.publisher}) 発売日:${dateFormat.format(game.release)} 価格:${game.price}円"
        message += if (isAvailable(game.wikipediaUrl)) " ${game.wikipediaUrl}" else " ${game.googleSearchUrl}"

        twitterService.tweet(message)
        return "OK"
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
