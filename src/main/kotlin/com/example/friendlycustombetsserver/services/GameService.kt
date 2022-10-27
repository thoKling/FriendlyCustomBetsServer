package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Bet
import com.example.friendlycustombetsserver.entities.MyTournament
import com.example.friendlycustombetsserver.entities.User
import com.example.friendlycustombetsserver.repositories.BetRepository
import com.example.friendlycustombetsserver.repositories.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    val gameRepository: GameRepository,
    val betRepository: BetRepository,
    val tournamentService: TournamentService
) {
    fun addBet(user: User, tournamentId: Long, gameId: Long, bet: Bet): MyTournament {
        val tournament =
            tournamentService.getTournament(tournamentId) ?: throw Exception("Impossible de trouver le tournoi")

        val game = tournament.games.find { it.id == gameId } ?: throw Exception("Impossible de trouver le match")

        betRepository.save(bet)

        game.bets.add(bet)
        gameRepository.save(game)

        return tournamentService.buildMyTournament(user, tournament)
    }
}