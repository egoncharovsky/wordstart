package ru.egoncharovsky.wordstart.domain.card

import ru.egoncharovsky.wordstart.domain.Identifiable
import ru.egoncharovsky.wordstart.domain.word.Phrase

data class LearningCard(
        val id: Long,
        val front: Phrase,
        val back: Phrase
) : Identifiable<Long> {
    override fun id(): Long = id
}