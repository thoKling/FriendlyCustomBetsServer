package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "tournament")
class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @OneToMany
    val games: MutableList<Game>,

    val name: String,
)