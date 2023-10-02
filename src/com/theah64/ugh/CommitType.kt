package com.theah64.ugh

import com.squareup.moshi.Json

class CommitType(
    val type: String,
    val description: String,
    val emoji: String,
    val keywords: Array<String> = arrayOf(),
    var points : Int = 0,
    @Transient
    var isMatchedWithKeyword : Boolean = false
) {
    companion object {
        const val TYPE_README = "readme"
    }

    override fun toString(): String {
        return "$emoji : $type : $description"
    }

    init {
        // making all keywords lowercase
        keywords.let {
            it.forEachIndexed { index, keyword ->
                keywords[index] = keyword.toLowerCase()
            }
        }
    }
}