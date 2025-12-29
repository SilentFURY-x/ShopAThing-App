package com.fury.shopathing.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fury.shopathing.domain.repository.AuthRepository
import com.fury.shopathing.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val authState = _authState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _authState.value = Resource.Loading()
            val result = repository.login(email, pass)
            _authState.value = result
        }
    }

    fun signup(email: String, pass: String) {
        viewModelScope.launch {
            _authState.value = Resource.Loading()
            val result = repository.signup(email, pass)
            _authState.value = result
        }
    }
}