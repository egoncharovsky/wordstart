package ru.egoncharovsky.wordstart.repository

import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.domain.word.Language
import ru.egoncharovsky.wordstart.domain.word.Phrase
import ru.egoncharovsky.wordstart.domain.word.Translation
import java.util.concurrent.atomic.AtomicLong

object LearningCardRepository : MockRepository<LearningCard, Long>() {

    init {
        insert(LearningCard(1L, Phrase("word", Language.EN), Phrase("слово", Language.RU)))
        insert(LearningCard(2L, Phrase("word2", Language.EN), Phrase("слово2", Language.RU)))
        insert(LearningCard(3L, Phrase("word3", Language.EN), Phrase("слово3", Language.RU)))
        insert(LearningCard(4L, Phrase("word4", Language.EN), Phrase("слово4", Language.RU)))
    }

    private val sequence = AtomicLong(5L)
    fun create(entity: LearningCard): LearningCard {
        entity.id = sequence.getAndAdd(1)
        return insert(entity)
    }

    fun findFor(translation: Translation): Set<LearningCard> = getAll().filter { it.containedIn(translation) }.toSet()
}