package com.example.mycompose2

const val MAX_NO_OF_WORDS = 10
const val SCORE_INCREASE = 20

val allWords: Set<String> =
    setOf(
        "animal",
        "auto",
        "anecdote",
        "alphabet",
        "all",
        "awesome",
        "arise",
        "balloon",
        "basket",
        "bench",
        "best",
        "birthday",
        "book",
        "briefcase",
        "camera",
    )


// Maps words to their lengths. Each word in allWords has a unique length. This is required since
// the words are randomly picked inside GameViewModel and the selection is unpredictable.

private val wordLengthMap: Map<Int, String> = allWords.associateBy( { it.length }, { it } )

internal fun getUnscrambledWord(scrambledWord: String) = wordLengthMap[scrambledWord.length] ?: ""