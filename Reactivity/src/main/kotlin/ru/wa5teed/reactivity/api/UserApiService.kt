package ru.wa5teed.reactivity.api

import Messages
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.wa5teed.reactivity.model.Currency
import ru.wa5teed.reactivity.model.User
import ru.wa5teed.reactivity.repository.UserRepository

@RestController
@RequestMapping("users")
class UserApiService(private val userRepository: UserRepository) {
    @PostMapping
    fun register(@RequestBody userDto: Messages.UserDto): Mono<User> {
        val currency = try {
            Currency.valueOf(userDto.itemCurrency.name)
        } catch (error: IllegalArgumentException) {
            return Mono.error(error)
        }
        return userRepository.save(User(userDto.id, currency))
    }
}
