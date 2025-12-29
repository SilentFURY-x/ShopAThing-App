package com.fury.shopathing.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fury.shopathing.domain.model.Product
import com.fury.shopathing.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // The Paging library gives us a Flow directly.
    // cachedIn(viewModelScope) keeps the list alive when you rotate the screen.
    val products: Flow<PagingData<Product>> = repository.getProducts()
        .cachedIn(viewModelScope)
}