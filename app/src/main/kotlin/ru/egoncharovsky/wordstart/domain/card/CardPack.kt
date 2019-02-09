package ru.egoncharovsky.wordstart.domain.card

import ru.egoncharovsky.wordstart.domain.Identifiable

data class CardPack(
        var id: Long?,
        val name: String,
        val cards: Set<LearningCard>
) : Identifiable<Long> {

    override fun id(): Long? = id
}
