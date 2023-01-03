package com.example.friendlycustombetsserver.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "user_tournament_tokens")
class UserTournamentTokens(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JsonBackReference
    val user: User,

    @ManyToOne
    val tournament: Tournament,

    var tokens: Float
)