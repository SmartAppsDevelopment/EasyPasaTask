package com.example.technical.challenge.presentation.regstrcomplain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentRegstrCmplanBinding
import com.example.technical.challenge.databinding.NewComplainUiMenuItemBinding
import com.example.technical.challenge.domain.adapter.GeneralAdapter
import com.example.technical.challenge.domain.model.menu.Body
import com.example.technical.challenge.domain.model.menu.Category
import com.example.technical.challenge.utils.convertStringToClass

/**
 * @author Umer Bilal
 * Created 25/06/2024 at 12:51 pm
 */
class RegisterComplainFragment : Fragment() {


    val args by navArgs<RegisterComplainFragmentArgs>()

    val arguments by lazy {
        args.bodyobj.convertStringToClass<Body>()
    }

    private lateinit var binding: FragmentRegstrCmplanBinding
    val mAdapterCategory = GeneralAdapter<Category>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_regstr_cmplan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeToolbarLayout.tvTitle.text = "Register New Complaint"
        binding.includeToolbarLayout.ivArrow.setOnClickListener { findNavController().navigateUp() }
        inflateMenuList()
    }

    private fun inflateMenuList() {
        mAdapterCategory.expressionOnCreateViewHolder = { viewGroup ->
            NewComplainUiMenuItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        }

        mAdapterCategory.expressionViewHolderBinding = { eachItem, viewBinding ->
            with(viewBinding as NewComplainUiMenuItemBinding) {
                this.root.setOnClickListener {
                    findNavController().navigate(RegisterComplainFragmentDirections.actionRegisterComplainFragmentToRegisterComplainTypeFragment())
                }
                tvTitle.text = eachItem.categoryName
            }
        }

        mAdapterCategory.listOfItems = arguments?.categories?.toMutableList()
        binding.rvMenuListItem.adapter = mAdapterCategory
    }


}