package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.MyTournament
import com.example.friendlycustombetsserver.services.TournamentService
import com.example.friendlycustombetsserver.services.UserService
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/tournament")
class TournamentController(val tournamentService: TournamentService, val userService: UserService) {
    @GetMapping("/myTournaments")
    fun myTournaments(principal: Principal): List<MyTournament> {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.getMyTournaments(user)
    }

    @GetMapping("/tournament/{tournamentId}")
    fun tournament(principal: Principal, @PathVariable tournamentId: Long): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.getTournament(user, tournamentId)
    }

    @PostMapping("/create")
    fun create(principal: Principal, @RequestBody tournamentName: String): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.createTournament(tournamentName, user)
    }

    @PostMapping("/join")
    fun join(principal: Principal, @RequestBody tournamentId: Long): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.joinTournament(user, tournamentId)
    }

    @PostMapping("/addGame")
    fun addGame(
        principal: Principal,
        @RequestBody tournamentId: Long,
        @RequestBody gameName: String,
    ): Game {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.addGame(user, tournamentId, gameName)
    }
}
