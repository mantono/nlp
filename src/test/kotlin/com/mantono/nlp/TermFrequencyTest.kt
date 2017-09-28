package com.mantono.nlp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TermFrequencyTest
{
    @Test
    fun testCorrectCountOnSimpleLettersInList()
    {
        val input = listOf("a", "a", "a", "b", "b", "c")
        val tf: Map<String, Int> = termFrequency(input)
        assertEquals(3, tf["a"])
        assertEquals(2, tf["b"])
        assertEquals(1, tf["c"])
        assertEquals(0, tf["d"])
    }

    @Test
    fun testCorrectCountOnSingleString()
    {
        val input = listOf("a a a b b c")
        val tf: Map<String, Int> = termFrequency(input)
        assertEquals(3, tf["a"])
        assertEquals(2, tf["b"])
        assertEquals(1, tf["c"])
        assertEquals(0, tf["d"])
    }

    @Test
    fun testCorrectCountOnMultipleLettersString()
    {
        val input = listOf("aaa aaa aaa b b c")
        val tf: Map<String, Int> = termFrequency(input)
        assertEquals(3, tf["aaa"])
        assertEquals(2, tf["b"])
        assertEquals(1, tf["c"])
        assertEquals(0, tf["d"])
    }
}