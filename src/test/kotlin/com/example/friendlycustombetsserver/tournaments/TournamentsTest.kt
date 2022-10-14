package com.example.friendlycustombetsserver.tournaments

import com.example.friendlycustombetsserver.repositories.TournamentRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
internal class TournamentsTest(
) {
    private val tournamentRepository: TournamentRepository = Mockito.mock(TournamentRepository::class.java)
    //private val tournamentService: TournamentService = TournamentService(tournamentRepository)


}