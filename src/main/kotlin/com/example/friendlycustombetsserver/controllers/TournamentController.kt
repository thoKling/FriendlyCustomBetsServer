package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.services.TournamentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TournamentController(val tournamentService: TournamentService) {
    @PostMapping
    fun createTournament(name: String) {
        tournamentService.createTournament(name)
    }

    @GetMapping
    fun getTournamentWhereIParticipates() {

    }
}
