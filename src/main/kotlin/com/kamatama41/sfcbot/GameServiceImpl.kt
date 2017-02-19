package com.kamatama41.sfcbot

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class GameServiceImpl(
        private val gameRepository: GameRepository
) : GameService, GameRepository by gameRepository
