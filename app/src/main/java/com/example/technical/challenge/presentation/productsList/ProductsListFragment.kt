package com.example.technical.challenge.presentation.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.technical.challenge.R
import com.example.technical.challenge.data.base.Errors
import com.example.technical.challenge.databinding.FragmentProductListBinding
import com.example.technical.challenge.utils.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    val viewModel by viewModels<ProductsFragmentViewModel>()
    private lateinit var binding: FragmentProductListBinding
    private val productItemAdapter by lazy {
        ProductItemAdapter()
    }

    init {

        lifecycleScope.launchWhenResumed {
            viewModel.handleErrorState.collect {
                when (it) {
                    is Errors.GenericError -> TODO()
                    Errors.InternetConnectionError -> TODO()
                    Errors.NetworkError -> TODO()
                    Errors.NotSure -> TODO()
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.productListResponseFlow.collect {
                    closeKeyboard(binding.root)
                    productItemAdapter.productsList = it ?: emptyList()
                    productItemAdapter.notifyDataSetChanged()
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.item = viewModel.searchItems
        binding.rcyViewProductsList.adapter = productItemAdapter
    }


}