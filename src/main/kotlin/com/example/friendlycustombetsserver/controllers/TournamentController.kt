package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.entities.Tournament
import com.example.friendlycustombetsserver.services.TournamentService
import com.example.friendlycustombetsserver.services.UserService
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/tournament")
class TournamentController(val tournamentService: TournamentService, val userService: UserService) {
    @GetMapping("/myTournaments")
    fun myTournaments(principal: Principal): List<Tournament> {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.getMyTournaments(user)
    }

    @PostMapping("/create")
    fun create(principal: Principal, @RequestBody tournamentName: String): Tournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.createTournament(tournamentName, user)
    }
}
