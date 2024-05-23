package com.example.technical.challenge.data.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.base.ResultWrapper2
import com.example.technical.challenge.data.base.SafeApiCaller
import com.example.technical.challenge.data.base.SafeApiCaller2
import com.example.technical.challenge.data.network.NetworkService
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultProductListingRepository @Inject constructor(private val service: NetworkService,
                                                          private val apiCaller: SafeApiCaller,
                                                          private val apiCaller2: SafeApiCaller2,
// , Here we can inject DBService
): ProductListingRepository {

    override suspend fun getProductList (searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse> =
        apiCaller.safeApiCall(Dispatchers.IO) {
            service.getProductList(searchItemsModel.maker, searchItemsModel.model, searchItemsModel.year)
        }


    override suspend fun getProductList2 (searchItemsModel: SearchItemsModel): ResultWrapper2<ProductListResponse> =
        apiCaller2.safeApiCall(Dispatchers.IO) {
            service.getProductList(searchItemsModel.maker, searchItemsModel.model, searchItemsModel.year)
        }
}