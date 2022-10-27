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
@RequestMapping("/tournament/{tournamentId}/game")
@SecurityRequirements(value = [SecurityRequirement(name = "Auth0")])
class GameController(val tournamentService: TournamentService, val userService: UserService) {
    @PostMapping("/addGame")
    @Operation(summary = "Add a new game to a Tournament", operationId = "addGameToTournament")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added a new Game to this Tournament"),
        ]
    )
    fun addGame(
        principal: Principal,
        @RequestBody game: Game,
        @PathVariable tournamentId: Long
    ): MyTournament {
        val user = userService.getUserOrCreate(principal)

        return tournamentService.addGame(user, tournamentId, game)
    }
}
