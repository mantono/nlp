package com.mantono.nlp

import java.io.File
import java.nio.file.Files

data class TermFrequency internal constructor(private val terms: Map<String, Int>): Map<String, Int> by terms
{
    override fun get(key: String): Int = terms[key] ?: 0
}

fun termFrequency(file: File, tokenizer: TokenizerSetting = DefaultTokenizer): TermFrequency
{
    return termFrequency(Files.readAllLines(file.toPath()))
}

fun <T: CharSequence> termFrequency(input: Sequence<T>, tokenizer: TokenizerSetting = DefaultTokenizer): TermFrequency
{
    val map: Map<String, Int> = input
            .map { it.split(tokenizer.separator) }
            .flatMap { it.asSequence() }
            .map { it.replace(tokenizer.remove, "") }
            .groupBy { it }
            .map { it.key to it.value.size }
            .toMap()

    return TermFrequency(map)
}

fun <T: CharSequence> termFrequency(input: T, tokenizer: TokenizerSetting = DefaultTokenizer): TermFrequency
{
    return termFrequency(sequenceOf(input), tokenizer)
}

fun <T: CharSequence> termFrequency(input: Collection<T>, tokenizer: TokenizerSetting = DefaultTokenizer): TermFrequency
{
    return termFrequency(input.asSequence(), tokenizer)
}