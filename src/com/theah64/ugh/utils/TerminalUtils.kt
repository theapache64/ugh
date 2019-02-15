package com.theah64.ugh.utils

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object TerminalUtils {

    /**
     * Executes given command in given directory
     */
    fun execute(command: Array<String>, currentDir: String? = System.getProperty("user.dir")) {

        // log
        command.forEach {
            print("$it ")
        }

        var builder = ProcessBuilder(*command)
        builder = builder.directory(File(currentDir))
        val p = builder.start()
        val reader = BufferedReader(InputStreamReader(p.inputStream))
        var line = reader.readLine()
        println()
        while (line != null) {
            println(line)
            line = reader.readLine()
        }
        reader.close()
    }
}