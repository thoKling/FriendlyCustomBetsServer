package com.example.friendlycustombetsserver.controllers

import com.example.friendlycustombetsserver.dto.requests.TakeBetRequest
import com.example.friendlycustombetsserver.entities.BetTaken
import com.example.friendlycustombetsserver.services.BetTakenService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/tournament/{tournamentId}/takenBet")
@SecurityRequirements(value = [SecurityRequirement(name = "Auth0")])
class BetTakenController(
    private val betTakenService: BetTakenService,
) {
    @PostMapping("/takeBet/{betId}/{gameId}")
    @Operation(summary = "Take a bet on an existing bet", operationId = "takeBet")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added a new Bet to this Game"),
        ]
    )
    fun takeBet(
        principal: Principal,
        @RequestBody request: TakeBetRequest,
        @PathVariable tournamentId: Long,
        @PathVariable gameId: Long,
        @PathVariable betId: Long,
    ): BetTaken {
        return betTakenService.takeBet(
            principal,
            tournamentId,
            gameId,
            betId,
            request
        )
    }
}
