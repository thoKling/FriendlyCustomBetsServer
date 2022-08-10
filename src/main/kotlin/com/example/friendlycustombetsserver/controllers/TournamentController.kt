package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.services.TournamentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tournament")
class TournamentController(val tournamentService: TournamentService) {
    @PostMapping
    fun createTournament(name: String) {
        tournamentService.createTournament(name)
    }

    @GetMapping("/myTournaments")
    fun myTournaments(): String {
        return "test"
    }
}
