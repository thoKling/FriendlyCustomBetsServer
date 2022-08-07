package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @OneToMany
    val participatingTournaments: MutableList<Tournament>,
)