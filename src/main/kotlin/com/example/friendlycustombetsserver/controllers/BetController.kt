package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.entities.Bet
import com.example.friendlycustombetsserver.entities.MyTournament
import com.example.friendlycustombetsserver.services.GameService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/tournament/{tournamentId}/game/{gameId}/bet")
@SecurityRequirements(value = [SecurityRequirement(name = "Auth0")])
class BetController(private val gameService: GameService) {
    @PostMapping("/addBet")
    @Operation(summary = "Add a new bet to a Game", operationId = "addBetToGame")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added a new Bet to this Game"),
        ]
    )
    fun addBet(
        principal: Principal,
        @RequestBody bet: Bet,
        @PathVariable tournamentId: Long,
        @PathVariable gameId: Long,
    ): MyTournament {
        return gameService.addBet(principal, tournamentId, gameId, bet)
    }
}
