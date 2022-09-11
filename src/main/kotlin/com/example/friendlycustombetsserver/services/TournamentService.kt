package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.entities.*
import com.example.friendlycustombetsserver.repositories.TournamentRepository
import com.example.friendlycustombetsserver.repositories.UserTournamentTokensRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TournamentService(
    val tournamentRepository: TournamentRepository,
    val userTournamentRepository: UserTournamentTokensRepository
) {
    fun createTournament(tournamentName: String, owner: User): MyTournament {
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

        return MyTournament(tournament, 100.0f)
    }

    fun getTournament(user: User, tournamentId: Long): MyTournament {
        val utt = userTournamentRepository.findByUserAndTournamentId(user, tournamentId)
            ?: throw (Exception("Impossible de trouver le tournoi")) // TODO: gérer ce cas :

        return MyTournament(
            tournament = utt.tournament,
            myTokens = utt.tokens
        )
    }

    fun getMyTournaments(user: User): List<MyTournament> {
        val userTournamentTokens = userTournamentRepository.findByUser(user)

        return userTournamentTokens.map { ut ->
            MyTournament(
                tournament = ut.tournament,
                myTokens = ut.tokens
            )
        }
    }

    fun getTournament(id: Long): Tournament? {
        return tournamentRepository.findByIdOrNull(id)
    }

    fun joinTournament(user: User, tournamentId: Long): MyTournament {
        val tournament: Tournament? = getTournament(tournamentId)
        if (tournament == null) {
            throw Exception("Ce tournoi n'existe pas")
        } else {
            tournament.participants.add(user)
            tournamentRepository.save(tournament)

            return MyTournament(tournament = tournament, myTokens = tournament.startingTokens)
        }
    }

    fun addGame(user: User, tournamentId: Long, gameName: String): Game {
        val tournament: Tournament? = getTournament(tournamentId)
        if (tournament == null) {
            throw Exception("Ce tournoi n'existe pas")
        } else if (tournament.owner != user) {
            throw Exception("Vous n'avez pas les droits")
        } else {
            val game = Game(name = gameName)
            tournament.games.add(game)
            tournamentRepository.save(tournament)
            return game
        }
    }
}