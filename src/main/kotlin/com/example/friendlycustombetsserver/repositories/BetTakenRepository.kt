package com.example.friendlycustombetsserver.repositories


import com.example.friendlycustombetsserver.entities.BetTaken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BetTakenRepository : CrudRepository<BetTaken, Long>