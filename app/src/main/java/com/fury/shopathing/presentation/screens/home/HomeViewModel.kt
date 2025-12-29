package com.fury.shopathing.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fury.shopathing.domain.model.Product
import com.fury.shopathing.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // _uiState is mutable (we can change it)
    // uiState is immutable (the UI can only read it)
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Fetch products as soon as the ViewModel is created
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val products = repository.getProducts()
                if (products.isNotEmpty()) {
                    _uiState.value = HomeUiState.Success(products)
                } else {
                    _uiState.value = HomeUiState.Error("No products found")
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

// Represents the different states of the screen
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val products: List<Product>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}