package com.theah64.ugh.utils

import java.util.*

class InputUtils private constructor(
    private val scanner: Scanner
) {

    /**
     * Get a String with given prompt as prompt
     */
    fun getString(prompt: String, isRequired: Boolean): String {
        print("$prompt: ")
        val value = scanner.nextLine()
        while (value.trim().isEmpty() && isRequired) {
            println("Invalid ${prompt.toLowerCase()} `$value`")
            return getString(prompt, isRequired)
        }
        return value
    }

    companion object {
        private var instance: InputUtils? = null
        fun getInstance(scanner: Scanner): InputUtils {
            if (instance == null) {
                instance = InputUtils(scanner)
            }
            return instance!!
        }
    }

}