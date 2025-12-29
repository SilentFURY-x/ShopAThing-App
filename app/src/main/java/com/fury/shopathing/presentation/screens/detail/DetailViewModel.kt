package com.fury.shopathing.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
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
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    // SavedStateHandle automatically holds the arguments passed via Navigation
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val state: StateFlow<DetailUiState> = _state.asStateFlow()

    init {
        // Extract the 'productId' from the route arguments
        val productId = savedStateHandle.get<Int>("productId") ?: -1
        if (productId != -1) {
            fetchProduct(productId)
        } else {
            _state.value = DetailUiState.Error("Invalid Product ID")
        }
    }

    private fun fetchProduct(id: Int) {
        viewModelScope.launch {
            _state.value = DetailUiState.Loading
            try {
                val product = repository.getProductById(id)
                _state.value = DetailUiState.Success(product)
            } catch (e: Exception) {
                _state.value = DetailUiState.Error(e.message ?: "Failed to load product")
            }
        }
    }
}

// UI States for the Detail Screen
sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val product: Product) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}