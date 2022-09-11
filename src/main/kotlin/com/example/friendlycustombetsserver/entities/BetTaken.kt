package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "bet_taken")
class BetTaken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val user: User,

    @ManyToOne
    val bet: Bet,

    val tokensAmount: Float,
)