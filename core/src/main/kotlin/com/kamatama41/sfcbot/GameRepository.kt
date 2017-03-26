package com.kamatama41.sfcbot

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface GameRepository : PagingAndSortingRepository<Game, Long>
