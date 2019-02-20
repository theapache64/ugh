package com.theah64.ugh

import com.theah64.ugh.utils.InputUtils
import com.theah64.ugh.utils.StringUtils
import com.theah64.ugh.utils.TerminalUtils
import java.util.*

object Ugh {
    fun doCleanCommit() {

        val scanner = Scanner(System.`in`)

        val inputUtils = InputUtils.getInstance(scanner)
        val commitType = CommitType.getCommitType(scanner)
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
        val emoji = CommitType.getCommitTypeFromMessage(message)
        println("Commit type identified : $emoji")
        val commitMessage = "${emoji.emoji} $message"
        val commitCommand = StringUtils.getCommitCommand(commitMessage)
        TerminalUtils.execute(commitCommand)
    }

}