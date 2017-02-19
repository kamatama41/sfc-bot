package com.kamatama41.sfcbot.tool

import org.hibernate.tool.hbm2ddl.ImportSqlCommandExtractor
import java.io.BufferedReader
import java.io.Reader

class CsvSqlCommandExtractor: ImportSqlCommandExtractor {
    override fun extractCommands(reader: Reader): Array<String> {
        BufferedReader(reader).useLines {
            var header: List<String>? = null
            val commands = mutableListOf<String>()
            it.asIterable().forEachIndexed { i, line ->
                if (i == 0) {
                    header = line.split(":")
                } else {
                    // e.g. a,b,c,d'e -> 'a','b','c','d''e'
                    val values = "'${line.replace("'", "''").replace(",", "','")}'"
                    header?.let { commands.add("insert into ${it[0]}(${it[1]}) values ($values)") }
                }
            }
            return commands.toTypedArray()
        }
    }
}
