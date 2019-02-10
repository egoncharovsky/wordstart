package ru.egoncharovsky.wordstart.domain.card

import ru.egoncharovsky.wordstart.domain.Identifiable
import ru.egoncharovsky.wordstart.domain.word.Phrase
import ru.egoncharovsky.wordstart.domain.word.Translation

data class LearningCard(
        var id: Long?,
        val front: Phrase,
        val back: Phrase
) : Identifiable<Long> {
    override fun id(): Long? = id

    fun containedIn(translation: Translation) =
            translation.originalPhrase == front && translation.variantsContainsWord(back)
}