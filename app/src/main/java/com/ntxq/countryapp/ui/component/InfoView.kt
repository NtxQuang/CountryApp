package com.ntxq.countryapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.textview.MaterialTextView
import com.ntxq.countryapp.R

class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var textTitle: CharSequence? = null
        set(value) {
            field = value
            findViewById<MaterialTextView?>(R.id.textTitle)?.text = value
        }
    private var textInfo: CharSequence? = null
        set(value) {
            field = value
            findViewById<MaterialTextView?>(R.id.textInfo)?.text = value
        }

    init {
        inflate(context, R.layout.layout_info, this)
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ItemInfo)
        try {
            if (typeArray.hasValue(R.styleable.ItemInfo_info)) {
                textInfo = typeArray.getText(R.styleable.ItemInfo_info)
            }
            if (typeArray.hasValue(R.styleable.ItemInfo_title)) {
                textTitle = typeArray.getText(R.styleable.ItemInfo_title)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            typeArray.recycle()
        }
    }

    fun setInfo(info: String) {
        textInfo = info
        this.invalidate()
    }
}