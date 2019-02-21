package com.theah64.ugh.utils

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.theah64.ugh.CommitType
import java.lang.reflect.Type

class CommitType2(
    val emojiCode: String,
    val type: String,
    val description: String,
    val emoji: String,
    val keywords: Array<String> = arrayOf()
) {
    companion object {
        fun convert(commitType: CommitType): CommitType2 {
            return CommitType2(
                commitType.emojiCode,
                commitType.type,
                commitType.description,
                commitType.emoji,
                commitType.keywords
            )
        }

        fun convert(commitType: Array<CommitType>): List<CommitType2> {
            val list = mutableListOf<CommitType2>()
            for (c in commitType) {
                list.add(Companion.convert(c))
            }
            return list
        }
    }
}

fun main(args: Array<String>) {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val type = Types.newParameterizedType(List::class.java, CommitType2::class.java)
    val commitTypeAdapter = moshi.adapter<List<CommitType2>>(type)
    val commitType2s = CommitType2.convert(CommitType.values())
    val jsonString = commitTypeAdapter.toJson(commitType2s)
    println(jsonString)
}