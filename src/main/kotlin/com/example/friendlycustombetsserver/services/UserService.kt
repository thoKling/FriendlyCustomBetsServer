package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import com.example.friendlycustombetsserver.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun getTournamentsWhereIParticipate(): List<Tournament> {
        return mutableListOf()
    }
}