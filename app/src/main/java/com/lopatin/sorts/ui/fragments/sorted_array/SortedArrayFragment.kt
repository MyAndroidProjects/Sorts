package com.lopatin.sorts.ui.fragments.sorted_array

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lopatin.sorts.R
import com.lopatin.sorts.model.*
import com.lopatin.sorts.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_sorted_array.*
import java.lang.Exception
import java.lang.StringBuilder

class SortedArrayFragment : BaseFragment() {

    private var unsortedArray: Array<Int> = arrayOf(0)

    companion object {
        var sortType = Sort.BUBBLE
        var unsortedList = ArrayList<Int>()
        @Synchronized
        fun getInstance(sortType: Sort, unsortedList: ArrayList<Int>): SortedArrayFragment {
            this.sortType = sortType
            this.unsortedList = unsortedList
            return SortedArrayFragment()
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_sorted_array
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unsortedArray = convertArrayListToArray(unsortedList)
        sortArray(unsortedArray)
        sortName.text = sortType.sortName
    }

    private fun convertArrayListToArray(unsortedList: ArrayList<Int>): Array<Int> {
        return unsortedList.toTypedArray()
    }

    private fun sortArray(unsortedArray: Array<Int>) {
        when (sortType) {
            Sort.BUBBLE -> {
                Log.d("logSort", "sortType $sortType")
//                sortedArrayText.text = getStringFromArray(bubbleSortDecrease(unsortedArray))
                sortedArrayText.text = getStringFromArray(unsortedArray.bubbleSortIncrease())
            }
            Sort.SELECTION -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.selectionSortIncrease())
            }
            Sort.INSERTION -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.insertionSortIncrease())
            }
            Sort.MERGE -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.mergeSort())
            }
            Sort.QUICK_SORT -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.quickSort())
            }
            Sort.SHELL -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.shellSort())
            }
            Sort.TREE -> {
                Log.d("logSort", "sortType $sortType")
                sortedArrayText.text = getStringFromArray(unsortedArray.treeSort())
            }

        }
    }


    private fun getStringFromArray(array: Array<Int>): String {
        val sb = StringBuilder("")
        for (number in array) {
            try {
                if (sb.isEmpty()) {
                    sb.append(number.toString())
                } else {
                    sb.append(", ")
                    sb.append(number.toString())
                }
            } catch (e: Exception) {
                Log.d("logException", "getStringFromArray $e")
                e.stackTrace
            }
        }
        return sb.toString()
    }

}