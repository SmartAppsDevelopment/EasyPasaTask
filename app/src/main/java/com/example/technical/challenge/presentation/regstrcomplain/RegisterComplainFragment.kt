package com.example.technical.challenge.presentation.regstrcomplain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentRegstrCmplanBinding
import com.example.technical.challenge.presentation.components.SupportMenuItemsComponent

/**
 * @author Umer Bilal
 * Created 25/06/2024 at 12:51 pm
 */
class RegisterComplainFragment : Fragment() {



    private lateinit var binding: FragmentRegstrCmplanBinding

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
        with(binding.includeMenuMoneytransfer) {
            root.setOnClickListener {
                findNavController().navigate(RegisterComplainFragmentDirections.actionRegisterComplainFragmentToRegisterComplainTypeFragment())
            }
           tvTitle.text="Money Transfer"
        }

        with(binding.includeMenuEasyload) {
            tvTitle.text="Easyload"
        }

        with(binding.includeMenuPckg) {
            tvTitle.text="Packages"
        }

        with(binding.includeMenuMiniapp) {
            tvTitle.text="Mini App"
        }

        with(binding.includeMenuAccRelate) {
            tvTitle.text="Account Related"
        }
//        binding.includeToolbarLayout.tvTitle.text="Select your issue from the list below"
        binding.includeToolbarLayout.tvTitle.text="Register New Complaint"
        binding.includeToolbarLayout.ivArrow.setOnClickListener { findNavController().navigateUp() }
    }


}