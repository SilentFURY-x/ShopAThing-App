package com.fury.shopathing.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fury.shopathing.data.local.CartEntity
import com.fury.shopathing.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    // 1. Hot Flow of Cart Items (Auto-updates when DB changes)
    val cartItems: StateFlow<List<CartEntity>> = repository.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 2. Calculated Total Price (Auto-updates when cartItems change)
    val totalPrice: StateFlow<Double> = cartItems.map { list ->
        list.sumOf { it.price * it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun increment(item: CartEntity) {
        viewModelScope.launch {
            repository.updateQuantity(item.productId, item.quantity + 1)
        }
    }

    fun decrement(item: CartEntity) {
        viewModelScope.launch {
            if (item.quantity > 1) {
                repository.updateQuantity(item.productId, item.quantity - 1)
            } else {
                repository.deleteItem(item) // Remove if qty goes to 0
            }
        }
    }

    fun delete(item: CartEntity) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun checkout() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}