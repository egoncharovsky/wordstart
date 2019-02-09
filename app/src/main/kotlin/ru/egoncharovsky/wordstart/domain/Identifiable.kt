package ru.egoncharovsky.wordstart.domain

interface Identifiable<IdentifierType> {

    fun id(): IdentifierType?

}