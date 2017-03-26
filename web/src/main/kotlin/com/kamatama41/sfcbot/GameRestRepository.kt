package com.kamatama41.sfcbot

import org.springframework.data.rest.core.annotation.RestResource

/**
 * This Repository hides POST, PUT and DELETE method for Game entity
 */
interface GameRestRepository : GameRepository {
    @RestResource(exported = false)
    override fun deleteAll()

    @RestResource(exported = false)
    override fun delete(entities: MutableIterable<Game>)

    @RestResource(exported = false)
    override fun delete(id: Long)

    @RestResource(exported = false)
    override fun delete(entity: Game)

    @RestResource(exported = false)
    override fun <S : Game?> save(entity: S): S

    @RestResource(exported = false)
    override fun <S : Game?> save(entities: MutableIterable<S>): MutableIterable<S>
}
