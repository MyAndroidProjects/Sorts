package com.lopatin.sorts

import com.lopatin.sorts.model.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun test_bubble_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.bubbleSortIncrease())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.bubbleSortIncrease())
        println("end")
    }

    @Test
    fun test_selection_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.quickSort())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.quickSort())
        println("end")
    }

    @Test
    fun test_insertion_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.insertionSortIncrease())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.insertionSortIncrease())
        println("end")
    }

    @Test
    fun test_merge_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.mergeSort())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.mergeSort())
        println("end")
    }
    @Test
    fun test_quick_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.quickSort())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.quickSort())
        println("end")
    }


    @Test
    fun test_shell_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.shellSort())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.shellSort())
        println("end")
    }
    @Test
    fun test_tree_sort() {
        println("start")
        val array = getTestArray()
        showArray(array.treeSort())
        val arrayZero = arrayOf<Int>()
        showArray(arrayZero.treeSort())
        println("end")
    }

    private fun showArray(array: Array<Int>) {
        val stringBuilder = StringBuilder()
        for (number in array) {
            stringBuilder.append(number)
            stringBuilder.append(", ")
        }
        println(stringBuilder)
    }

    private fun getTestArray(): Array<Int> {
        return arrayOf(2, 7, 3, 5, 0, 1, 4, -4, -8, 9)
    }

}
