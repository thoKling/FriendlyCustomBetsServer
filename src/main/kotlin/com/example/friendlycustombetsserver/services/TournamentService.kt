package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.*
import com.example.friendlycustombetsserver.repositories.GameRepository
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import com.example.friendlycustombetsserver.repositories.UserTournamentTokensRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class TournamentService(
    private val tournamentRepository: TournamentRepository,
    private val gameRepository: GameRepository,
    private val userTournamentRepository: UserTournamentTokensRepository,
    private val verifyService: VerifyService,
    private val userService: UserService
) {
    fun createTournament(principal: Principal, tournamentName: String): MyTournament {
        val owner = userService.getUserOrCreate(principal)

        val tournament = tournamentRepository.save(
            Tournament(
                name = tournamentName,
                owner = owner,
                startingTokens = 100.0f
            )
        )

        userTournamentRepository.save(
            UserTournamentTokens(
                user = owner,
                tournament = tournament,
                tokens = tournament.startingTokens
            )
        )

        return buildMyTournament(owner, tournament)
    }

    fun getMyTournament(principal: Principal, tournamentId: Long): MyTournament {
        val tournament = verifyService.verifyTournament(tournamentId)
        val user = verifyService.verifyUserParticipateInTournament(principal, tournament)

        return buildMyTournament(user, tournament)
    }

    fun buildMyTournament(user: User, tournament: Tournament): MyTournament {
        val utt = userTournamentRepository.findByUserAndTournamentId(user, tournament.id!!)
            ?: throw Exception("Impossible de trouver le tournoi")

        return MyTournament(tournament, utt.tokens)
    }

    fun getMyTournaments(principal: Principal): List<MyTournament> {
        val user = userService.getUserOrCreate(principal)
        val userTournamentTokens = userTournamentRepository.findByUser(user)

        return userTournamentTokens.map { ut ->
            MyTournament(
                tournament = ut.tournament,
                myTokens = ut.tokens
            )
        }
    }

    fun joinTournament(principal: Principal, tournamentId: Long): MyTournament {
        val tournament = verifyService.verifyTournament(tournamentId)
        val user = userService.getUserOrCreate(principal)

        userTournamentRepository.save(
            UserTournamentTokens(
                user = user,
                tournament = tournament,
                tokens = tournament.startingTokens
            )
        )
        tournament.participants.add(user)
        tournamentRepository.save(tournament)

        return buildMyTournament(user, tournament)
    }

    fun addGame(principal: Principal, tournamentId: Long, game: Game): MyTournament {
        val tournament = verifyService.verifyTournament(tournamentId)
        val owner = verifyService.verifyUserIsOwner(principal, tournament)

        val savedGame = gameRepository.save(game)
        tournament.games.add(savedGame)
        tournamentRepository.save(tournament)

        return buildMyTournament(owner, tournament)
    }
}