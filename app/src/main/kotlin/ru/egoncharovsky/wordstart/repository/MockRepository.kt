package ru.egoncharovsky.wordstart.repository

import ru.egoncharovsky.wordstart.domain.Identifiable

open class MockRepository<EntityType : Identifiable<IdentifierType>, IdentifierType> {

    val store = mutableMapOf<IdentifierType, EntityType>()

    fun get(id: IdentifierType): EntityType = find(id)!!

    fun find(id: IdentifierType): EntityType? = store[id]

    fun getAll(): Set<EntityType> = store.values.toSet()

    fun insert(entity: EntityType): EntityType {
        when(store.contains(entity.id())) {
            true ->  throw IllegalArgumentException("Entity with id ${entity.id()} already persisted")
            false -> {
                store[entity.id()] = entity
                return get(entity.id())
            }
        }
    }

    fun update(entity: EntityType): EntityType {
        store[entity.id()] = entity
        return get(entity.id())
    }

    fun delete(id: IdentifierType) = store.remove(id)
}