package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.entities.User
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TournamentService(val tournamentRepository: TournamentRepository) {
    fun createTournament(tournamentName: String, owner: User): Tournament {
        return tournamentRepository.save(
            Tournament(
                id = null,
                name = tournamentName,
                games = mutableListOf(),
                owner = owner,
                participants = mutableListOf()
            )
        )
    }

    fun getMyTournaments(user: User): List<Tournament> {
        val ownedTournaments = tournamentRepository.findByOwner(user)
        val participatingTournaments = tournamentRepository.findByParticipantsContaining(user)
        return ownedTournaments + participatingTournaments
    }

    fun getTournament(id: Long): Tournament? {
        return tournamentRepository.findByIdOrNull(id)
    }


    fun addGame(tournamentId: Long, game: Game): Tournament {
        val tournament: Tournament? = getTournament(tournamentId)
        if (tournament == null) {
            throw Exception("Ce tournoi n'existe pas")
        } else {
            tournament.games.add(game)
            tournamentRepository.save(tournament)
            return tournament
        }
    }
}