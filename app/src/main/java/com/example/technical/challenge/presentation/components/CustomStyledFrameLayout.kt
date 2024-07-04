package com.example.technical.challenge.presentation.components

/**
 * @author Umer Bilal
 * Created 20/06/2024 at 10:06 pm
 */
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.example.technical.challenge.R


class CustomStyledFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var titleTextView: TextView

    init {
        // Inflate the layout for your custom view
//        inflate(context, R.layout.custom_view_layout, this)
//
//        // Find the TextView within your inflated layout
//        titleTextView = findViewById(R.id.titleTextView)
//
//        // Obtain styleable attributes
//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomStyledFrameLayout)
//        val titleText = attributes.getString(R.styleable.CustomStyledFrameLayout_titleText)
//        val titleTextColor =
//            attributes.getColor(R.styleable.CustomStyledFrameLayout_titleTextColor, 0)
//
//        // Apply attributes to the TextView
//        titleTextView.text = titleText
//        if (titleTextColor != 0) {
//            titleTextView.setTextColor(titleTextColor)
//        }
//
//        // Recycle the TypedArray to prevent memory leaks
//        attributes.recycle()
    }
}