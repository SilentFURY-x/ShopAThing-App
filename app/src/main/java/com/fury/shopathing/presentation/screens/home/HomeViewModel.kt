package com.fury.shopathing.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fury.shopathing.domain.model.Product
import com.fury.shopathing.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import com.fury.shopathing.domain.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
    cartRepository: CartRepository // <--- 1. Injecting CartRepository
) : ViewModel() {

    // 2. Creating a specific Flow just for the badge count
    // We Map the LIST of items to an INT (size)
    // 2. Creating a specific Flow just for the badge count
    val cartItemCount = cartRepository.getAllItems()
        .map { items -> items.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // 1. Search Query State
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // 2. Category State
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory = _selectedCategory.asStateFlow()

    // 3. List of available categories
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        fetchCategories()
    }

    // 4. THE MAGIC: Combine Search + Category to trigger new Paging Flows
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class) // Add FlowPreview annotation
    val products: Flow<PagingData<Product>> = combine(
        _searchQuery
            .debounce(500L) // ▼▼▼ THE MAGIC FIX: Wait 500ms before searching
            .onEach { if (it.isNotEmpty()) _selectedCategory.value = "All" }, // Auto-reset category when searching
        _selectedCategory
    ) { query, category ->
        Pair(query, category)
    }.flatMapLatest { (query, category) ->
        repository.getProducts(query, category)
    }.cachedIn(viewModelScope)


    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }
}