package ru.wa5teed.reactivity.api

import Messages
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.wa5teed.reactivity.model.Item
import ru.wa5teed.reactivity.repository.ItemRepository
import ru.wa5teed.reactivity.repository.UserRepository

@RestController
@RequestMapping("items")
class ItemApiService(private val itemRepository: ItemRepository, private val userRepository: UserRepository) {
    @GetMapping("{user_id}")
    fun getItemsView(@PathVariable("user_id") userId: Long): Flux<Item> {
        val viewUser = userRepository.findById(userId)
        val viewItems = itemRepository.findAll()
        return viewItems.flatMap { item -> viewUser.map { item.mulPrice(it.itemCurrency.value) } }
    }

    @PostMapping
    fun addItem(@RequestBody itemDto: Messages.ItemDto): Mono<Item> =
        itemRepository.save(Item(itemDto.id, itemDto.priceKopecks, itemDto.itemSku))
}
