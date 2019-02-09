package ru.egoncharovsky.wordstart.domain.card

import ru.egoncharovsky.wordstart.domain.Identifiable

data class CardPack(
        val name: String,
        val cards: Set<LearningCard>
) : Identifiable<String> {

    override fun id(): String = name
}
