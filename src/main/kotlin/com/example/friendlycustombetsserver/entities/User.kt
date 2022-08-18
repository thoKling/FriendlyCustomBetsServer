package com.example.friendlycustombetsserver.entities

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    val id: String,
)