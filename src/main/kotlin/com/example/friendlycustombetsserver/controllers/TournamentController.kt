package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.entities.Game
import com.example.friendlycustombetsserver.entities.MyTournament
import com.example.friendlycustombetsserver.services.TournamentService
import com.example.friendlycustombetsserver.services.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/tournament")
@SecurityRequirements(value = [SecurityRequirement(name = "Auth0")])
class TournamentController(val tournamentService: TournamentService, val userService: UserService) {
    @GetMapping("/myTournaments")
    @Operation(summary = "Fetch all tournaments where user participate", operationId = "getMyTournaments")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully fetched my Tournaments"),
        ]
    )
    fun myTournaments(principal: Principal): List<MyTournament> {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.getMyTournaments(user)
    }

    @GetMapping("/{tournamentId}")
    @Operation(summary = "Fetch a tournament by its id", operationId = "getTournament")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully fetched the Tournament"),
        ]
    )
    fun tournament(principal: Principal, @PathVariable tournamentId: Long): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.getTournament(user, tournamentId)
    }

    @PostMapping("/create")
    @Operation(summary = "Create a tournament", operationId = "createTournament")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully created a new Tournament"),
        ]
    )
    fun create(principal: Principal, @RequestBody tournamentName: String): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.createTournament(tournamentName, user)
    }

    @PostMapping("/join")
    @Operation(summary = "Join a tournament by its id", operationId = "joinTournament")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully joined a Tournament"),
        ]
    )
    fun join(principal: Principal, @RequestBody tournamentId: Long): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.joinTournament(user, tournamentId)
    }

    @PostMapping("/addGame")
    @Operation(summary = "Add a new game to a Tournament", operationId = "addGameToTournament")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added a new Game to this Tournament"),
        ]
    )
    fun addGame(
        principal: Principal,
        @RequestBody tournamentId: Long,
        @RequestBody gameName: String,
    ): Game {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.addGame(user, tournamentId, gameName)
    }
}
