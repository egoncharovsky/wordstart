package ru.egoncharovsky.wordstart.domain.card

data class CardPack(
        val name: String,
        val cards: Set<LearningCard>
)
