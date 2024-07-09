package com.example.technical.challenge.presentation.regstrcomplaintype

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentRegstrCmplanTypeBinding
import com.example.technical.challenge.presentation.helpsupoort.CustomAdapter
import com.example.technical.challenge.utils.CustomBottomSheetDialogFragment
import com.example.technical.challenge.utils.getListOfIssue
import com.example.technical.challenge.utils.getListOfMoneyTransfer

/**
 * @author Umer Bilal
 * Created 25/06/2024 at 12:51 pm
 */
class RegisterComplainTypeFragment : Fragment() {


    private lateinit var binding: FragmentRegstrCmplanTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =


            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_regstr_cmplan_type,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.includeTypeDropdown) {
            autocomtextview.setAdapter(CustomAdapter(requireContext(), getListOfMoneyTransfer))
            autocomtextview.setOnItemClickListener { adapterView, view, i, l ->
                Log.e("autocomtextview", "setOnItemClickListener: ")
                binding.includeIssueDropdown.root.isVisible = true
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownclose)
            }
            tvTitle.text = "Type of Money Transfer"
            autocomtextview.hint = "Input Type"
            autocomtextview.setOnDismissListener {
                Log.e("autocomtextview", "setOnDismissListener: ")
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownclose)
            }
            autocomtextview.setOnClickListener {
                Log.e("autocomtextview", "setOnClickListener: ")
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownopen)
            }
            menu.setEndIconOnClickListener {
                if (autocomtextview.isPopupShowing) {
                    autocomtextview.dismissDropDown()
                } else {
                    autocomtextview.showDropDown()
                }
            }
        }

        with(binding.includeIssueDropdown) {
            autocomtextview.setAdapter(CustomAdapter(requireContext(), getListOfIssue))
            tvTitle.text = "Select Issue"
            autocomtextview.hint = "Input Text"
            autocomtextview.setOnItemClickListener { adapterView, view, i, l ->
                binding.includeAddComments.root.isVisible = true
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownclose)
            }
            autocomtextview.setOnDismissListener {
                Log.e("autocomtextview", "setOnDismissListener: ")
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownclose)
            }
            autocomtextview.setOnClickListener {
                Log.e("autocomtextview", "setOnClickListener: ")
                menu.endIconDrawable = getDrawable(requireContext(), R.drawable.dropdownopen)
            }
            menu.setEndIconOnClickListener {
                if (autocomtextview.isPopupShowing) {
                    autocomtextview.dismissDropDown()
                } else {
                    autocomtextview.showDropDown()
                }
            }
        }

        with(binding.includeAddComments) {
            tvTitle.text = "What was the problem?"
        }

        binding.includeToolbarLayout.tvTitle.text = "Register New Complaint"
        binding.includeToolbarLayout.ivArrow.setOnClickListener { findNavController().navigateUp() }

        binding.btnRegisterComplaint.setOnClickListener {
            val bottomSheet = CustomBottomSheetDialogFragment()
            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)

        }
    }


}