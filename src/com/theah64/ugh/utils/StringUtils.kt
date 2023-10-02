package com.theah64.ugh.utils

object StringUtils {

    /**
     * Returns an array of commit command than can be executed with ProcessBuilder
     */
    fun getCommitCommand(finalCommitMessage: String) = arrayOf("git", "commit", "-m", finalCommitMessage)
}