package com.lopatin.sorts.ui.activities

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.lopatin.sorts.R
import com.lopatin.sorts.model.Sort
import com.lopatin.sorts.ui.adapters.SelectSortAdapter
import com.lopatin.sorts.ui.fragments.BaseFragment
import com.lopatin.sorts.ui.fragments.bottom_sheet.SelectSortBottomSheetFragment
import com.lopatin.sorts.ui.fragments.enter_numbers.EnterNumbersFragment
import com.lopatin.sorts.ui.fragments.sorted_array.SortedArrayFragment
import com.lopatin.sorts.ui.fragments.unsorted_array.UnsortedArrayFragment
import com.lopatin.sorts.ui.view_models.MainActivityViewModel
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.lopatin.sorts.utils.dpToPx
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SelectSortAdapter.SelectSortListener,
    EnterNumbersFragment.EnterNumbersListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View?>
    private var unsortedArrayFragment: UnsortedArrayFragment? = null
    private var sortedArrayFragment: SortedArrayFragment? = null
    private var enterNumbersFragment: EnterNumbersFragment? = null
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var activityRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        activityRootView = findViewById<View>(R.id.activityRoot)
        setSoftKeyboardOpenListener()
    }

    private fun setSoftKeyboardOpenListener() {
        val cont = this as Context
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val heightDiff =
                    activityRootView.rootView.height - activityRootView.height
                if (heightDiff > dpToPx(cont, 200f)
                ) { // if more than 200 dp, it probably a keyboard...
                    bottomSheetText.visibility = View.GONE
                } else {
                    val handler = Handler()
                    handler.postDelayed({
                        bottomSheetText.visibility = View.VISIBLE
                    }, 20)

                }
            }
        })
    }


    override fun onStart() {
        super.onStart()
        unsortedArrayFragment = UnsortedArrayFragment.getInstance()
        setFragment(R.id.unsortedArrayFragmentFrame, unsortedArrayFragment!!)
//        setFragment(R.id.unsortedArrayFragmentFrame, UnsortedArrayFragment.getInstance())

        enterNumbersFragment = EnterNumbersFragment.getInstance(this)
        setFragment(R.id.actionFragmentFrame, enterNumbersFragment!!)
        createBottomSheet()

        setSortsRecyclerAdapter()
        setBottomSheetHeaderClickListener()
    }

    private fun setBottomSheetHeaderClickListener() {
        bottomSheetHeader.setOnClickListener {
            when (bottomSheetBehavior.state) {
                STATE_COLLAPSED -> bottomSheetBehavior.state = STATE_EXPANDED
                STATE_EXPANDED -> bottomSheetBehavior.state = STATE_COLLAPSED
            }
            Log.d("logSort", " bottomSheetBehavior state ${bottomSheetBehavior.state}")
        }
    }

    private fun setFragment(fragmentFrame: Int, fragment: BaseFragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(fragmentFrame, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
    /**
     *  если брать BottomSheetBehavior из view, то затемнения не происходит
     */
    private fun createBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from<View>(bottomSheetText as View)
        bottomSheetBehavior.state = STATE_COLLAPSED
    }

    /**
     *  если использовать фрагмент то затеняются другие элементы
     */
    fun createBottomSheetFragment() {
        val bottomDialogFragment = SelectSortBottomSheetFragment.newInstance()

        bottomDialogFragment.show(supportFragmentManager, null)
    }

    private fun setSortsRecyclerAdapter() {
        val sortList = ArrayList<Sort>()
        for (value in Sort.values()) {
            sortList.add(value)
        }
        val adapter = SelectSortAdapter(sortList, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewSorts.layoutManager = layoutManager
        recyclerViewSorts.adapter = adapter

    }


    override fun selectSort(sort: Sort) {
        Log.d("myLog", " sort $sort ${sort.sortName}")
        sortedArrayFragmentFrame.visibility = View.VISIBLE
        sortedArrayFragment = SortedArrayFragment.getInstance(sort, viewModel.unsortedList)
        setFragment(R.id.sortedArrayFragmentFrame, sortedArrayFragment!!)
        bottomSheetBehavior.state = STATE_COLLAPSED
        activityScrollView.scrollTo(0, 0)
    }

    override fun addNumber(number: Int) {
        unsortedArrayFragment?.addNumberToText(number)
        viewModel.unsortedList.add(number)
        Log.d("myLog", " addNumber $number")
    }

    override fun clearArray() {
        viewModel.unsortedList.clear()
        unsortedArrayFragment?.clearText()

        Log.d("myLog", "clearArray")
    }


    //    fun Selection(list: IList<Int>): IList<Int> {
//        for (i in 0 until list.Count - 1) {
//            var min = i
//            for (j in i + 1 until list.Count) {
//                if (list[j] < list[min]) {
//                    min = j
//                }
//            }
//            val dummy = list[i]
//            list[i] = list[min]
//            list[min] = dummy
//        }
//        return list
//    }


}
