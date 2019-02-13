package com.theah64.ugh

import java.io.BufferedReader
import java.util.*
import java.io.File
import java.io.InputStreamReader


// commit
val scanner = Scanner(System.`in`)


/**
 * Inspired from https://github.com/kazupon/git-commit-message-convention
 *
 * MOTO
 * ####

All Commit Message Format MUST meet this Text Format:

[:<Emoji>: ][<Type>[(<Scope>)]: ]<Subject>
[<BLANK LINE>]
[<Message Body>]
[<BLANK LINE>]
[<Message Footer>]
 */

const val COMMIT_QUALITY_CLEAN = "clean"
const val COMMIT_QUALITY_QUICK = "quick"

fun main(args: Array<String>) {

    require(args.isNotEmpty()) { "commit quality should be passed" }

    val commitQuality = args[0]

    when (commitQuality) {

        COMMIT_QUALITY_CLEAN -> {
            doCleanCommit()
        }

        COMMIT_QUALITY_QUICK -> {

            require(args.size > 1) { "commit message missing" }
            val message = args[1]
            doQuickCommit(message)
        }

        else -> {
            println("ERROR : Undefined commit quality $commitQuality")
        }
    }


}

fun doQuickCommit(message: String) {
    val emoji = getCommitTypeFromMessage(message)
    println("$emoji")
}

fun getCommitTypeFromMessage(message: String): CommitType {

    val msg = message.toLowerCase()
    val words = message.split(" ")
    val fWord = words[0].toLowerCase()

    // looping through all commit types
    for (commitType in CommitType.values()) {

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

    return CommitType.UPDATE
}

private fun doCleanCommit() {
    val commitType = getCommitType()
    val scope = getString("Scope", true).toLowerCase()
    val subject = getString("Subject", true).capitalize()
    val messageBody = getString("Message Body", false).capitalize()
    val messageFooter = getString("Message Footer", false).capitalize()

    val finalCommitMessage =
        "${commitType.emojiCode} ${commitType.type}($scope) : $subject\n\n$messageBody\n\n$messageFooter\n".trim()

    val command = getCommitCommand(finalCommitMessage)

    execute(command)
}

private fun getCommitCommand(finalCommitMessage: String) = arrayOf("git", "commit", "-m", finalCommitMessage)

/**
 * Executes given command in given directory
 */
private fun execute(command: Array<String>, currentDir: String? = System.getProperty("user.dir")) {

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


/**
 * Get a String with given title as prompt
 */
fun getString(title: String, isRequired: Boolean): String {
    print("$title: ")
    val value = scanner.nextLine()
    while (value.trim().isEmpty() && isRequired) {
        println("Invalid ${title.toLowerCase()} `${value}`")
        return getString(title, isRequired)
    }
    return value
}


/**
 * Returns valid commit type
 */
fun getCommitType(): CommitType {


    print("Type: ")
    val inputType = scanner.nextLine()

    // Printing all type if it's a ?
    while (inputType.equals("?")) {
        CommitType.values().forEach { commitType ->
            println(commitType.toString())
        }

        return getCommitType()
    }

    // Checking if it's a valid type
    val commitTypes = CommitType.values().filter {
        inputType == it
            .type
    }

    while (commitTypes.isEmpty()) {
        println("Invalid commit type `$inputType`")
        return getCommitType()
    }

    val finalCommitType = commitTypes.first()

    println(finalCommitType.toString())

    return finalCommitType
}
