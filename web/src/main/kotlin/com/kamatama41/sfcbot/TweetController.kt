package com.kamatama41.sfcbot

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TweetController(
        private val gameService: GameService
) {
    @RequestMapping("/tweet", method = arrayOf(RequestMethod.POST))
    fun tweet(): String {
        gameService.tweetRandomly()
        return "OK"
    }
}
