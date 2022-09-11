package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "bet")
class Bet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val amount: Int,
)