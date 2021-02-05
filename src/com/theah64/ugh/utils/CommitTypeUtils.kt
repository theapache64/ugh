package com.theah64.ugh.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.theah64.ugh.CommitType
import com.theah64.ugh.Ugh
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

object CommitTypeUtils {

    /**
     * Values should be in lowercase
     */
    private val messageTypePreset = mapOf(
        "update readme" to CommitType.TYPE_README
    )

    private val commitTypes: List<CommitType> by lazy {

        // jar path
        val currentPath = File(
            Ugh::class.java!!.protectionDomain.codeSource.location
                .toURI()
        ).parentFile.path

        val dicFileName = "dictionary.json"
        var dicFile = File("$currentPath/$dicFileName")
        if (!dicFile.exists()) {
            // current path
            dicFile = File(dicFileName)
        }


        // Reading json file
        val reader = BufferedReader(FileReader(dicFile))
        val jsonStringBuilder = StringBuffer()

        var line = reader.readLine()
        do {
            jsonStringBuilder.append(line)
            line = reader.readLine()
        } while (line != null)

        reader.close()


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
        while (inputType == "?") {

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


    fun getCommitTypesFromMessage(message: String): Set<CommitType> {

        // Preference for preset messages
        val key = message.toLowerCase().trim()
        if (messageTypePreset.containsKey(key)) {
            val commitType = messageTypePreset[key]
            return commitTypes.filter { it.type == commitType }.toSet()
        } else {
            val list = mutableSetOf<CommitType>()
            val words = message.split(" ")
            for (x in words) {
                val word = x.toLowerCase()
                for (commitType in commitTypes) {

                    if (commitType.description.toLowerCase().contains(word) || word == commitType.type) {
                        list.add(commitType)
                    } else {
                        // looping through keywords
                        for (keyword in commitType.keywords) {
                            if (keyword.contains(word) || word.contains(keyword)) {
                                list.add(commitType)
                            }
                        }
                    }
                }
            }

            if (list.isEmpty()) {
                list.add(commitTypes.find { it.type == "backup" }!!)
            }

            return list
        }


    }
}