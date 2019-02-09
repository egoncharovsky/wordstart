package ru.egoncharovsky.wordstart.repository

import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.domain.word.Language
import ru.egoncharovsky.wordstart.domain.word.Phrase

object CardPackRepository : MockRepository<CardPack, String>() {
    init {
        insert(CardPack("pack1", setOf(
                LearningCard(Phrase("word", Language.EN), Phrase("слово", Language.RU)),
                LearningCard(Phrase("word2", Language.EN), Phrase("слово2", Language.RU))
        )))
    }
}