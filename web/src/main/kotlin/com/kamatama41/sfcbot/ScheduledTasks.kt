package com.kamatama41.sfcbot

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTasks(
        private val gameService: GameService
) {
    @EnableScheduling class Config

    @Scheduled(cron = "0 0 */2 * * *") // Once a two hours
    fun tweetJob() {
        gameService.tweetRandomly()
    }
}
