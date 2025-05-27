package com.kev.domain.model

data class Contact(
    val fullName: String,
    val email: String,
    val phone: String,
    val avatarUrl: String,
    val city: String,
    val country: String
)