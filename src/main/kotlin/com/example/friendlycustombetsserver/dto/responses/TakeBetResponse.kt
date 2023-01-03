package com.example.friendlycustombetsserver.dto.responses

import com.example.friendlycustombetsserver.entities.BetTaken
import com.example.friendlycustombetsserver.entities.MyTournament

data class TakeBetResponse(val betTaken: BetTaken, val tournament: MyTournament)
