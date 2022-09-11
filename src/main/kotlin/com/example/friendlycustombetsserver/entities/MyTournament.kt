package com.example.friendlycustombetsserver.entities

/*
 * This class represents a view to simplify the client app
 * it adds the tokens amount the current user owns for a specific tournament
 */
class MyTournament(
    tournament: Tournament,
    val myTokens: Float,
) :
    Tournament(
        tournament.id,
        tournament.name,
        tournament.games,
        tournament.owner,
        tournament.participants,
        tournament.startingTokens
    )