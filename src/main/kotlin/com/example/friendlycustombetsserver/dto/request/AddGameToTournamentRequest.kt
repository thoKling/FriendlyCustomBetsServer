package com.example.friendlycustombetsserver.dto.request

import com.example.friendlycustombetsserver.entities.Game

data class AddGameToTournamentRequest(val game: Game, val tournamentId: Long)
