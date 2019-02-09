package ru.egoncharovsky.wordstart.repository

import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.domain.word.Language
import ru.egoncharovsky.wordstart.domain.word.Phrase
import java.util.concurrent.atomic.AtomicLong

object CardPackRepository : MockRepository<CardPack, Long>() {
    init {
        insert(CardPack(1L,"pack1", setOf(
                LearningCard(1L, Phrase("word", Language.EN), Phrase("слово", Language.RU)),
                LearningCard(2L, Phrase("word2", Language.EN), Phrase("слово2", Language.RU))
        )))
    }

    private val sequence = AtomicLong()
    fun create(entity: CardPack): CardPack {
        entity.id = sequence.getAndAdd(1)
        return insert(entity)
    }
}