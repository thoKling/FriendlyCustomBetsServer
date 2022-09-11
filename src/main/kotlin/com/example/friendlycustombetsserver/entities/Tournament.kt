package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "tournament")
class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @OneToMany
    val games: MutableList<Game> = mutableListOf(),

    @OneToOne
    val owner: User,

    @OneToMany
    val participants: MutableList<User> = mutableListOf(),

    val startingTokens: Float
)