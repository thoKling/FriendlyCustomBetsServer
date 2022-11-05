package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.dto.requests.TakeBetRequest
import com.example.friendlycustombetsserver.entities.BetTaken
import com.example.friendlycustombetsserver.repositories.BetTakenRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class BetTakenService(
    private val betTakenRepository: BetTakenRepository,
    private val verifyService: VerifyService
) {
    fun takeBet(
        principal: Principal,
        tournamentId: Long,
        gameId: Long,
        betId: Long,
        request: TakeBetRequest
    ): BetTaken {
        val tournament = verifyService.verifyTournament(tournamentId)
        val user = verifyService.verifyUserParticipateInTournament(principal, tournament)
        val bet = verifyService.verifyBet(betId)

        val betTaken = BetTaken(
            user = user,
            bet = bet,
            tokensAmount = request.tokensAmount
        )

        betTakenRepository.save(betTaken)

        return betTaken
    }
}