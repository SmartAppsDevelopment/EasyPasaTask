package com.example.technical.challenge.presentation.components

import android.view.View
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.HelpSupportUiMenuItemBinding

/**
 * @author Umer Bilal
 * Created 14/06/2024 at 1:46 pm
 */
object SupportMenuItemsComponent {

    val newIconItem= fun () =listOf(R.drawable.track,R.drawable.newicon,R.drawable.faqicon,R.drawable.tutorial,R.drawable.quick_help).random()

    fun inflateQuickHelpMenu(helpSupportUiMenuItemBinding: HelpSupportUiMenuItemBinding,onclick:(View)->Unit) {
        helpSupportUiMenuItemBinding.ivQuickHelp.
        setImageResource(R.drawable.quick_help)
        helpSupportUiMenuItemBinding.tvTitle.text = "Quick Help"
         helpSupportUiMenuItemBinding.root.setOnClickListener(onclick::invoke)
    }

    fun inflateRegisterComplainMenu(helpSupportUiMenuItemBinding: HelpSupportUiMenuItemBinding) {
        helpSupportUiMenuItemBinding.ivQuickHelp.
        setImageResource(R.drawable.newicon)
        helpSupportUiMenuItemBinding.tvTitle.text = "Register Complaint"
    }

    fun inflateTrackComplainMenu(helpSupportUiMenuItemBinding: HelpSupportUiMenuItemBinding) {
        helpSupportUiMenuItemBinding.ivQuickHelp.
        setImageResource(R.drawable.track)
        helpSupportUiMenuItemBinding.tvTitle.text = "Track Your Complaint"
    }

    fun inflateFaqsMenu(helpSupportUiMenuItemBinding: HelpSupportUiMenuItemBinding) {
        helpSupportUiMenuItemBinding.ivQuickHelp.
        setImageResource(R.drawable.faqicon)
        helpSupportUiMenuItemBinding.tvTitle.text = "FAQs"
    }

    fun inflateTutorialMenu(helpSupportUiMenuItemBinding: HelpSupportUiMenuItemBinding) {
        helpSupportUiMenuItemBinding.ivQuickHelp.
        setImageResource(R.drawable.tutorial)
        helpSupportUiMenuItemBinding.tvTitle.text = "Tutorials for Easypaisa APP"
    }
}