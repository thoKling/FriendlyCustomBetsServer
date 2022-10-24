package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.Game
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : CrudRepository<Game, Long> {
    fun save(game: Game): Game
}