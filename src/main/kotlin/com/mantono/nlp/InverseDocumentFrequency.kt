package com.mantono.nlp

import com.mantono.pyttipanna.hash
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ActorJob
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor
import java.io.File
import java.util.concurrent.atomic.AtomicReference

// Copy on write?
class InverseDocumentFrequency(queueSize: Int = 1000)
{
    private val frequency: TermFrequency = TermFrequency(emptyMap())
    private val addedDocuments: MutableSet<Array<Byte>> = HashSet(100)
    private val channel: Channel<File> = Channel(queueSize)

    suspend fun listen()
    {
        for(file in channel)
        {
            val hash: Array<Byte> = hash(file)
            if(hash !in addedDocuments)
            {
                val tf = termFrequency(file)
                add(tf)
                addedDocuments.add(hash)
            }
        }
    }

    private fun add(tf: TermFrequency)
    {
        //
    }

    private fun process(file: File)
    {
    }

    suspend fun add(file: File)
    {
        actor.send(file)
    }
}