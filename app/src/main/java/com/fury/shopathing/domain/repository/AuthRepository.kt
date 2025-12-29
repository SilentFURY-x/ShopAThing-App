package com.fury.shopathing.domain.repository

import com.fury.shopathing.utils.Resource // We will create this wrapper next
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, pass: String): Resource<FirebaseUser>

    suspend fun signup(email: String, pass: String): Resource<FirebaseUser>

    fun logout()
}