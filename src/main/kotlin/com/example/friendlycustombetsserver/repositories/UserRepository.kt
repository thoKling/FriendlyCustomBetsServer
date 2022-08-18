package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, String>, UserRepositoryCustom {
}

interface UserRepositoryCustom {
    fun createOrGet(id: String): User
}