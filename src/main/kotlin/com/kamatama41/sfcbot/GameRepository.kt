package com.kamatama41.sfcbot

import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, Long>
