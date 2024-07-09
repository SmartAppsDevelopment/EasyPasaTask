package com.example.technical.challenge.domain.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author Umer Bilal
 * Created 05/07/2024 at 5:45 pm
 */

class GeneralViewHolder<T> internal constructor(private val binding: ViewBinding, private val experssion:(T, ViewBinding)->Unit)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(item:T){
        experssion(item,binding)
    }
}