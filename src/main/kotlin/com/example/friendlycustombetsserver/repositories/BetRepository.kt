package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.Bet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BetRepository : CrudRepository<Bet, Long> {
    fun save(bet: Bet): Bet
}