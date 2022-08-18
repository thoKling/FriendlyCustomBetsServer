package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl: UserRepositoryCustom {
    @Autowired
    @Lazy
    lateinit var userRepository: UserRepository

    override fun createOrGet(id: String): User {
        return userRepository.findByIdOrNull(id) ?: userRepository.save(User(id))
    }
}