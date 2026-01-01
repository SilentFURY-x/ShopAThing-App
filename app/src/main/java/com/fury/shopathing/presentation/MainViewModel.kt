package com.fury.shopathing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fury.shopathing.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    // Hot flow that holds the current theme state
    val isDarkMode = userPreferences.isDarkMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            userPreferences.toggleDarkMode(isDark)
        }
    }
}