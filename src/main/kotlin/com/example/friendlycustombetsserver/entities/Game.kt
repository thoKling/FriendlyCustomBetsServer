package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "game")
class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToMany
    val bets: MutableList<Bet> = mutableListOf(),

    val name: String,
)