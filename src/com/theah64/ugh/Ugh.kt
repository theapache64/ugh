package com.theah64.ugh

import com.theah64.ugh.utils.CommitTypeUtils
import com.theah64.ugh.utils.InputUtils
import com.theah64.ugh.utils.StringUtils
import com.theah64.ugh.utils.TerminalUtils
import java.util.*

object Ugh {

    /**
     * Very interactive
     */
    fun doCleanCommit() {

        val scanner = Scanner(System.`in`)

        val inputUtils = InputUtils.getInstance(scanner)
        val commitType = CommitTypeUtils.getCommitType(scanner)
        val scope = inputUtils.getString("Scope", true).toLowerCase()
        val subject = inputUtils.getString("Subject", true).capitalize()
        val messageBody = inputUtils.getString("Message Body", false).capitalize()
        val messageFooter = inputUtils.getString("Message Footer", false).capitalize()

        val finalCommitMessage =
            "${commitType.emoji} ${commitType.type}($scope) : $subject\n\n$messageBody\n\n$messageFooter\n".trim()

        val command = StringUtils.getCommitCommand(finalCommitMessage)

        TerminalUtils.execute(command)
    }

    /**
     * Non-interactive
     */
    fun doQuickCommit(message: String) {
        val emoji = CommitTypeUtils.getCommitTypeFromMessage(message)
        doCommit(emoji, message)
    }

    private fun doCommit(emoji: CommitType, message: String) {
        println("Commit type identified : $emoji")
        val commitMessage = "${emoji.emoji} $message"
        val commitCommand = StringUtils.getCommitCommand(commitMessage)
        TerminalUtils.execute(commitCommand)
    }

    /**
     * Less interactive + quick
     */
    fun doQuickInteractiveCommit(message: String) {

        val matchingCommitTypes = CommitTypeUtils.getCommitTypesFromMessage(message)

        if (matchingCommitTypes.isEmpty()) {
            println("No commit type match your message. Please train your dictionary with more keywords")
            return
        }

        println("Please confirm commit type")
        for (type in matchingCommitTypes.withIndex()) {
            println("${type.index + 1} . ${type.value}")
        }

        val scanner = Scanner(System.`in`)
        val inputUtils = InputUtils.getInstance(scanner)

        val inputNum = inputUtils.getInt("Enter number", 1, matchingCommitTypes.size)
        doCommit(matchingCommitTypes.get(inputNum - 1), message)
    }

}