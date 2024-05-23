package com.example.technical.challenge.presentation.productsList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.technical.challenge.R
import com.example.technical.challenge.data.base.Errors
import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.base.ResultWrapper2
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import com.example.technical.challenge.domain.usecase.ProductsListUseCase
import com.example.technical.challenge.utils.validateSearchInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsFragmentViewModel @Inject constructor(
    val application: Application,
    private val productsListUseCase: ProductsListUseCase,
    private val productListingRepository: ProductListingRepository
) : AndroidViewModel(application) {

    private var _productListResponse = MutableLiveData<List<SearchResults>>()
    val productListResponse: LiveData<List<SearchResults>> = _productListResponse




    private var _handleErrorState = Channel<Errors>()
    val handleErrorState = _handleErrorState.receiveAsFlow()

    private var _productListResponseFlow = MutableStateFlow<List<SearchResults>>(emptyList())
    val productListResponseFlow: StateFlow<List<SearchResults>> = _productListResponseFlow




    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()
    var progressVisibility = ObservableField(View.GONE)
    var searchItems: SearchItemsModel = SearchItemsModel("maker", "model", "2021")

    fun getProductsList() {


        // Validate the inputs
        validateSearchInput(
            getApplication(),
            searchItems.maker,
            searchItems.model,
            searchItems.year
        )?.let {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(it)
            _productListResponse.value = emptyList()
            return
        }
        progressVisibility.set(View.VISIBLE)
        viewModelScope.launch {
            when (val productListResponse = productsListUseCase.run(searchItems)) {
                is ResultWrapper.NetworkError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(getApplication<Application>().getString(R.string.error_zero_record))
                }

                is ResultWrapper.GenericError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(getApplication<Application>().getString(R.string.error_generic))
                }

                is ResultWrapper.Success -> {
                    errorFieldVisibility.set(View.GONE)
                    _productListResponse.postValue(productListResponse.value.searchResults)
                }

                ResultWrapper.InternetConnectionError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(application.getString(R.string.error_no_internet))
                }

            }
            progressVisibility.set(View.GONE)
        }
    }


    fun getProductsList2(){
        progressVisibility.set(View.VISIBLE)

        viewModelScope.launch {
            when(val productListResponse = productListingRepository.getProductList2(searchItems)){
                is ResultWrapper2.ERROR -> {
                    progressVisibility.set(View.GONE)
                    _handleErrorState.send(productListResponse.value)
                }
                is ResultWrapper2.Success -> {
                    progressVisibility.set(View.GONE)
                    _productListResponseFlow.value=productListResponse.value.searchResults
                }
            }
        }
    }



}