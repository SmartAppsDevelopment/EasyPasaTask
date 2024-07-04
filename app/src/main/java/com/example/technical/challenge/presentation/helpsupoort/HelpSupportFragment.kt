package com.example.technical.challenge.presentation.helpsupoort

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentHelpSupportBinding
import com.example.technical.challenge.databinding.ListAdapterDialogItemBinding
import com.example.technical.challenge.presentation.components.SupportMenuItemsComponent

/**
 * @author Umer Bilal
 * Created 14/06/2024 at 1:43 pm
 */
class HelpSupportFragment : Fragment() {


    private lateinit var binding: FragmentHelpSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            inflate(inflater, R.layout.fragment_help_support, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(SupportMenuItemsComponent) {
          inflateQuickHelpMenu(binding.includeMenuQuickHelpLayout){
              findNavController().navigate(HelpSupportFragmentDirections.actionHelpSupportFragmentToRegisterComplainFragment())
          }
          inflateRegisterComplainMenu(binding.includeMenuRegisterComplaintLayout)
          inflateTrackComplainMenu(binding.includeMenuTrackComplaintLayout)
          inflateFaqsMenu(binding.includeMenuFaqsLayout)
          inflateTutorialMenu(binding.includeMenuTutorialLayout)
        }
        binding.includeToolbarLayout.tvTitle.text="Help & Customer Support"
        infalteAdapterDropDown()
    }

    private fun infalteAdapterDropDown() {
//        binding.sampleview2view2.autocomtextview.setAdapter(CustomAdapter(requireContext(),listOf("Item1","Item2","Item3","Item1","Item2","Item3","Item1","Item2","Item3","Item1","Item2","Item3","Item1","Item2","Item3","Item1","Item2","Item3")))
    }

}

class CustomAdapter(context: Context,dataListitem:List<String>):ArrayAdapter<String>(context,0,dataListitem ){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

//        val view = convertView ?: LayoutInflater.from(context)
//            .inflate(R.layout.list_adapter_dialog_item, parent, false)

        val binding= inflate<ListAdapterDialogItemBinding>(LayoutInflater.from(context),R.layout.list_adapter_dialog_item,parent,false)

        binding.tvTitle.text=getItem(position)


        return binding.root
    }

}