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
fun main(args: Array<String>) {

    val commitType = getCommitType()
    val scope = getString("Scope", false).toLowerCase()
    val subject = getString("Subject", false).capitalize()
    val messageBody = getString("Message Body", false).capitalize()
    val messageFooter = getString("Message Footer", false).capitalize()

    val finalCommitMessage =
        "${commitType.emojiCode} ${commitType.type}($scope) : $subject\n\n$messageBody\n\n$messageFooter\n"

    val currentDir = System.getProperty("user.dir")
    val command = arrayOf("git", "commit", "-m", finalCommitMessage)

    execute(command, currentDir)
}

/**
 * Executes given command in given directory
 */
private fun execute(command: Array<String>, currentDir: String?) {

    // log
    command.forEach {
        print("$it ")
    }

    var builder = ProcessBuilder(*command)
    builder = builder.directory(File(currentDir))
    val p = builder.start()
    val reader = BufferedReader(InputStreamReader(p.inputStream))
    var line = reader.readLine()
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
