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
import com.fury.shopathing.domain.repository.CartRepository
import com.fury.shopathing.data.local.CartEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val cartRepository: CartRepository,
    // SavedStateHandle automatically holds the arguments passed via Navigation
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val cartItemCount = cartRepository.getAllItems()
        .map { items -> items.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
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

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val entity = CartEntity(
                productId = product.id,
                title = product.title,
                price = product.price,
                imageUrl = product.imageUrl,
                quantity = 1
            )
            cartRepository.addToCart(entity)
        }
    }
}

// UI States for the Detail Screen
sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val product: Product) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}