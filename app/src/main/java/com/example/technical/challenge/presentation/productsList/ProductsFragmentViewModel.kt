package com.example.technical.challenge.presentation.productsList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical.challenge.data.base.Errors
import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsFragmentViewModel @Inject constructor(
    private val productListingRepository: ProductListingRepository
) : ViewModel() {

    private var _handleErrorState = Channel<Errors>()
    val handleErrorState = _handleErrorState.receiveAsFlow()

    private var _productListResponseFlow = MutableStateFlow<List<SearchResults>>(emptyList())
    val productListResponseFlow: StateFlow<List<SearchResults>> = _productListResponseFlow


    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()
    var progressVisibility = ObservableField(View.GONE)
    var searchItems: SearchItemsModel = SearchItemsModel("maker", "model", "2021")


    fun getProductsList() {
        progressVisibility.set(View.VISIBLE)

        viewModelScope.launch {
            when (val productListResponse = productListingRepository.getProductList(searchItems)) {
                is ResultWrapper.ERROR -> {
                    progressVisibility.set(View.GONE)
                    _handleErrorState.send(productListResponse.value)
                }

                is ResultWrapper.Success -> {
                    progressVisibility.set(View.GONE)
                    _productListResponseFlow.value = productListResponse.value.searchResults
                }
            }
        }
    }


}