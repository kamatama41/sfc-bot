package com.kamatama41.sfcbot

import java.net.URLEncoder
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Game(
        @Id @GeneratedValue
        val id: Long = -1,
        @Column(nullable = false)
        val title: String,
        @Column(nullable = false)
        val publisher: String,
        @Column(nullable = false)
        val release: Date,
        @Column(nullable = false)
        val price: Int
) {
    val wikipediaUrl: String get() = "https://ja.wikipedia.org/wiki/${urlEncode(title)}"
    val googleSearchUrl: String get() = "https://www.google.co.jp/search?q=${urlEncode(title)}"

    private fun urlEncode(path: String): String = URLEncoder.encode(path, "UTF-8")
}
