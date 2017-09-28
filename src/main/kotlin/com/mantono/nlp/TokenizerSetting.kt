package com.mantono.nlp

interface TokenizerSetting
{
    val separator: Regex
    val remove: Regex
}

object DefaultTokenizer : TokenizerSetting
{
    override val separator: Regex = Regex("\\s")
    override val remove: Regex = Regex("[!.;:]")
}