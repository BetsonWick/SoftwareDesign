package ru.wa5teed.reactivity.repository

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import ru.wa5teed.reactivity.model.Item

@Repository
interface ItemRepository : ReactiveMongoRepository<Item, Long>
