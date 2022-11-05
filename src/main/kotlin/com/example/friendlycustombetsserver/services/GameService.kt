package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.Bet
import com.example.friendlycustombetsserver.entities.MyTournament
import com.example.friendlycustombetsserver.repositories.BetRepository
import com.example.friendlycustombetsserver.repositories.GameRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class GameService(
    private val gameRepository: GameRepository,
    private val betRepository: BetRepository,
    private val tournamentService: TournamentService,
    private val verifyService: VerifyService
) {
    fun addBet(principal: Principal, tournamentId: Long, gameId: Long, bet: Bet): MyTournament {
        val tournament = verifyService.verifyTournament(tournamentId)
        val owner = verifyService.verifyUserIsOwner(principal, tournament)
        val game = verifyService.verifyGameInTournament(gameId, tournament)

        betRepository.save(bet)

        game.bets.add(bet)
        gameRepository.save(game)

        return tournamentService.buildMyTournament(owner, tournament)
    }
}