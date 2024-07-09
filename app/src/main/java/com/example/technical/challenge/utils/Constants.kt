package com.example.technical.challenge.utils

import android.content.Context
import com.example.technical.challenge.domain.model.menu.Body
import com.example.technical.challenge.domain.model.menu.IssueType

/**
 * @author Umer Bilal
 * Created 20/06/2024 at 11:55 pm
 */



val getListOfMoneyTransfer= arrayListOf("CNIC Transfer","Easypaisa Transfer","Tohfa","Bank Transfer")


val getListOfIssue= arrayListOf("Money Not sent","Money sent but not received by receiver","Wrong Amount sent","Money Sent to wrong person")


private var menuItemsList:List<Body>?=null

fun Context.getMenuItemsList():List<Body>?{
    return menuItemsList?.let {
        it
    }?: run {
        getDataFromAssets("MenuItems.json")?.let {
            it.convertStringToClass<IssueType?>()?.body
        }
    }
}