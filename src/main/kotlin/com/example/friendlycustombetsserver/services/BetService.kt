package com.example.friendlycustombetsserver.services

import com.example.friendlycustombetsserver.repositories.BetRepository
import org.springframework.stereotype.Service

@Service
class BetService(private val betRepository: BetRepository) {

}