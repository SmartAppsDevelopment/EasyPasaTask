package com.example.technical.challenge.domain.usecase

import com.example.technical.challenge.data.base.ResultWrapper
import com.example.technical.challenge.data.network.response.productlist.ProductListResponse
import com.example.technical.challenge.domain.model.network.request.SearchItemsModel
import com.example.technical.challenge.domain.repositories.ProductListingRepository
import javax.inject.Inject


@Deprecated("in favor of reduce coding time")
class ProductsListUseCaseImp @Inject constructor(
    private val productListingRepository: ProductListingRepository
) : ProductsListUseCase {

    override suspend fun run(searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse> {
        return productListingRepository.getProductList(searchItemsModel)
    }
}