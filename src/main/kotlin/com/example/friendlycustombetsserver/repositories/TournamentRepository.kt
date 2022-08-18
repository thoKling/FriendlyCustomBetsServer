package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TournamentRepository : CrudRepository<Tournament, Long> {
    fun findByOwner(user: User): MutableList<Tournament>
    fun findByParticipantsContaining(user: User): MutableList<Tournament>
}