package com.example.technical.challenge.domain.repositories

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.base.ResultWrapper2
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel

interface ProductListingRepository {
    suspend fun getProductList (searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse>
    suspend fun getProductList2 (searchItemsModel: SearchItemsModel): ResultWrapper2<ProductListResponse>
}