package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Bet
import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.entities.User
import com.example.friendlycustombetsserver.repositories.BetRepository
import com.example.friendlycustombetsserver.repositories.GameRepository
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class VerifyService(
    private val betRepository: BetRepository,
    private val gameRepository: GameRepository,
    private val tournamentRepository: TournamentRepository,
    private val userService: UserService
) {
    fun verifyBet(betId: Long): Bet {
        return betRepository.findByIdOrNull(betId) ?: throw Exception("Impossible de trouver le pari")
    }

    fun verifyGameInTournament(gameId: Long, tournament: Tournament): Game {
        val game = gameRepository.findByIdOrNull(gameId) ?: throw Exception("Impossible de trouver le match")

        if(!tournament.games.contains(game)) {
            throw Exception("Le match n'est pas dans le tournoi")
        }

        return game
    }

    fun verifyTournament(tournamentId: Long): Tournament {
        return tournamentRepository.findByIdOrNull(tournamentId) ?: throw Exception("Impossible de trouver le tournoi")
    }

    fun verifyUserParticipateInTournament(principal: Principal, tournament: Tournament): User {
        val user = userService.getUser(principal) ?: throw Exception("Impossible de trouver l'utilisateur")
        if (!tournament.participants.contains(user) && tournament.owner != user) {
            throw Exception("Vous n'êtes pas participant à ce tournoi")
        }

        return user
    }

    fun verifyUserIsOwner(principal: Principal, tournament: Tournament): User {
        val user = userService.getUser(principal) ?: throw Exception("Impossible de trouver l'utilisateur")
        if (tournament.owner != user) {
            throw Exception("Vous n'êtes pas le propriétaire de ce tournoi")
        }

        return user
    }
}