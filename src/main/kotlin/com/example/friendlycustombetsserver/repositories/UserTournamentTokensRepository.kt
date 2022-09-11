package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.User
import com.example.friendlycustombetsserver.entities.UserTournamentTokens
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTournamentTokensRepository : CrudRepository<UserTournamentTokens, Long> {
    fun findByUser(user: User): List<UserTournamentTokens>
    fun findByUserAndTournamentId(user: User, tournamentId: Long): UserTournamentTokens?
}