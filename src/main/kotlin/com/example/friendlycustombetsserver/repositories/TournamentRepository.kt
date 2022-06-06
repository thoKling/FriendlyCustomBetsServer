package com.example.friendlycustombetsserver.repositories

import com.example.friendlycustombetsserver.entities.Tournament
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TournamentRepository: CrudRepository<Tournament, Long>