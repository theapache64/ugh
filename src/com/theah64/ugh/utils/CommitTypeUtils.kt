package com.theah64.ugh.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.theah64.ugh.CommitType
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

object CommitTypeUtils {

    private val commitTypes: List<CommitType> by lazy {

        // Reading json file
        val reader = BufferedReader(FileReader(File("dictionary.json")))
        val jsonStringBuilder = StringBuffer()

        var line = reader.readLine()
        do {
            jsonStringBuilder.append(line)
            line = reader.readLine()
        } while (line != null)


        // Converting to list
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, CommitType::class.java)
        val adapter = moshi.adapter<List<CommitType>>(type)
        adapter.fromJson(jsonStringBuilder.toString())!!
    }


    /**
     * Returns valid commit type
     */
    fun getCommitType(scanner: Scanner): CommitType {


        print("Type: ")
        val inputType = scanner.nextLine()

        // Printing all type if it's a ?
        while (inputType.equals("?")) {

            commitTypes.forEach { commitType ->
                println(commitType.toString())
            }

            return getCommitType(scanner)
        }

        // Checking if it's a valid type
        val commitTypes = commitTypes.filter {
            inputType == it
                .type
        }

        while (commitTypes.isEmpty()) {
            println("Invalid commit type `$inputType`")
            return getCommitType(scanner)
        }

        val finalCommitType = commitTypes.first()

        println(finalCommitType.toString())

        return finalCommitType
    }


    fun getCommitTypeFromMessage(message: String): CommitType {

        val msg = message.toLowerCase()
        val words = message.split(" ")
        val fWord = words[0].toLowerCase()

        // looping through all commit types
        for (commitType in commitTypes) {

            // looping through keywords
            commitType.keywords?.let { keywords ->
                for (keyword in keywords) {
                    if (msg.startsWith(keyword)) {
                        return commitType
                    }
                }
            }

            // couldn't get anything from keyword,no go for description
            if (commitType.description.toLowerCase().contains(fWord)) {
                return commitType
            }
        }

        return commitTypes[0]
    }
}