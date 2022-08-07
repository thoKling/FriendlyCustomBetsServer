package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long>