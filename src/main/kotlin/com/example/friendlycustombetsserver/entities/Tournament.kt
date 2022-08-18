package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "tournament")
class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val name: String,

    @OneToMany
    val games: MutableList<Game>,

    @OneToOne
    val owner: User,

    @OneToMany
    val participants: MutableList<User>,
)