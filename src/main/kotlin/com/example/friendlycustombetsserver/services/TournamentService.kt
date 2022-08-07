package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TournamentService(val tournamentRepository: TournamentRepository) {
    fun getTournamentsWhereIParticipate(): List<Tournament> {
        return tournamentRepository.findAll().toList()
    }

    fun getTournament(id: Long): Tournament? {
        return tournamentRepository.findByIdOrNull(id)
    }

    fun createTournament(name: String): Tournament {
        return tournamentRepository.save(Tournament(id = null, name = name, games = mutableListOf()))
    }

    fun addGame(tournamentId: Long, game: Game): Tournament {
        val tournament: Tournament? = getTournament(tournamentId)
        if(tournament == null) {
            throw Exception("Ce tournoi n'existe pas")
        }
        else {
            tournament.games.add(game)
            tournamentRepository.save(tournament)
            return tournament
        }
    }
}