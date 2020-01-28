package com.lopatin.sorts.ui.fragments.unsorted_array

import com.lopatin.sorts.R
import com.lopatin.sorts.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_unsorted_array.*
import java.lang.StringBuilder

class UnsortedArrayFragment : BaseFragment() {

    companion object {
        @Synchronized
        fun getInstance(): UnsortedArrayFragment {
            return UnsortedArrayFragment()
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_unsorted_array
    }


    fun addNumberToText(number: Int) {
        val numberStr = number.toString()
        val oldText = arrayText.text
        if (oldText.isNullOrEmpty()) {
            arrayText.text = numberStr
        } else {
            val newText: StringBuilder = StringBuilder(oldText)
            newText.append(", ")
            newText.append(numberStr)
            arrayText.text = newText

        }
    }

    fun clearText() {
        arrayText.text = ""
    }

}