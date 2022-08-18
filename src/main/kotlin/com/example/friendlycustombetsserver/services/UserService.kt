package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.User
import com.example.friendlycustombetsserver.repositories.UserRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class UserService(val userRepository: UserRepository) {
    fun getUserOrCreate(principal: Principal): User {
        val id: String = principal.name.substringAfter("|")
        return userRepository.createOrGet(id)
    }
}