package com.lopatin.sorts.ui.fragments.enter_numbers

import android.os.Bundle
import android.view.View
import com.lopatin.sorts.R
import com.lopatin.sorts.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_enter_numbers.*
import java.lang.Exception

class EnterNumbersFragment : BaseFragment() {
    private var enterNumbersListener: EnterNumbersListener? = null

    companion object {
        @Synchronized
        fun getInstance(enterNumbersListener: EnterNumbersListener): EnterNumbersFragment {
            val fragment = EnterNumbersFragment()
            fragment.setListener(enterNumbersListener)
            return fragment
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_enter_numbers
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNumberButton.setOnClickListener {
val numberText = enterNumberEditText.editableText
            val number = convertStringToInt(numberText.toString())
            enterNumberEditText.text?.clear()
//            enterNumberEditText.setSelection(0)
            enterNumbersListener?.addNumber(number)
        }
        clearArrayButton.setOnClickListener {
            enterNumbersListener?.clearArray()
        }
    }

    private fun convertStringToInt(strNumber: String): Int{
        var number = 0
        try {
            number = strNumber.toInt()
        }catch (e: Exception){
            e.stackTrace
        }
        return number
    }

    private fun setListener(listener: EnterNumbersListener) {
        enterNumbersListener = listener
    }

    interface EnterNumbersListener {
        fun addNumber(number: Int)
        fun clearArray()
    }
}