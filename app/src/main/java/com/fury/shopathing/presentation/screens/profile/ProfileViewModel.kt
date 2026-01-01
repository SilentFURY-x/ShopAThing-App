package com.fury.shopathing.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fury.shopathing.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // State to hold the email
    private val _userEmail = MutableStateFlow("Loading...")
    val userEmail = _userEmail.asStateFlow()

    init {
        // Fetch current user email on init
        val user = authRepository.currentUser
        _userEmail.value = user?.email ?: "Guest"
    }

    fun logout() {
        authRepository.logout()
    }
}