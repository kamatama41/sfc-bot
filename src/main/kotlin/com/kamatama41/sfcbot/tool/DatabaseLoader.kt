package com.kamatama41.sfcbot.tool

import com.kamatama41.sfcbot.Software
import com.kamatama41.sfcbot.SoftwareRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat

@Component
class DatabaseLoader(
        private val softwareRepository: SoftwareRepository
) : CommandLineRunner {
    override fun run(vararg args: String) {
        BufferedReader(InputStreamReader(
                Thread.currentThread().contextClassLoader.getResource("import.csv").openStream()
        )).useLines {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            it.forEach { line ->
                val values = line.split(",".toRegex())
                val release = dateFormat.parse(values[0])
                val title = values[1]
                val publisher = values[2]
                val price = values[3].toInt()
                softwareRepository.save(
                        Software(release = release, title = title, publisher = publisher, price = price)
                )
            }
        }
    }
}
