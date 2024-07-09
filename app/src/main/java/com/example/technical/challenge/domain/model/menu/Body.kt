package com.example.technical.challenge.domain.model.menu

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Body(
    val categories: List<Category>,
    val id: Int,
    val name: String,
    val nameUrdu: String,
    val triggers: String
):Serializable