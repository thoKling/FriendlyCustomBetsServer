package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "game")
class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @OneToMany
    val bets: MutableList<Bet>,

    val name: String,
)