package com.theah64.ugh

import java.util.*

class CommitType(
    val type: String,
    val description: String,
    val emoji: String,
    val keywords: Array<String> = arrayOf()
) {

    override fun toString(): String {
        return "$emoji : $type : $description"
    }

    init {
        keywords?.let {
            it.forEachIndexed { index, keyword ->
                keywords[index] = keyword.toLowerCase()
            }
        }
    }

    companion object {
        /**
         * Returns valid commit type
         */
        fun getCommitType(scanner: Scanner, commitTypes: List<CommitType>): CommitType {


            print("Type: ")
            val inputType = scanner.nextLine()

            // Printing all type if it's a ?
            while (inputType.equals("?")) {

                commitTypes.forEach { commitType ->
                    println(commitType.toString())
                }

                return getCommitType(scanner, commitTypes)
            }

            // Checking if it's a valid type
            val commitTypes = commitTypes.filter {
                inputType == it
                    .type
            }

            while (commitTypes.isEmpty()) {
                println("Invalid commit type `$inputType`")
                return getCommitType(scanner, commitTypes)
            }

            val finalCommitType = commitTypes.first()

            println(finalCommitType.toString())

            return finalCommitType
        }


        fun getCommitTypeFromMessage(message: String, commitTypes: List<CommitType>): CommitType {

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

}