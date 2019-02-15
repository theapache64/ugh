package com.theah64.ugh

import java.util.*


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
            Ugh.doCleanCommit()
        }

        COMMIT_QUALITY_QUICK -> {

            require(args.size > 1) { "commit message missing" }
            val message = args[1]
             Ugh.doQuickCommit(message)


        }

        else -> {
            println("ERROR : Undefined commit quality $commitQuality")
        }
    }


}






