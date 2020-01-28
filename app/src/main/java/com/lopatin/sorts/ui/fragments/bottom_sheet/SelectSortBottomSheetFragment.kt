package com.lopatin.sorts.ui.fragments.bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lopatin.sorts.R
import kotlinx.android.synthetic.main.fragment_select_sort_bottom_sheet.*

/**
 *  Если использовать BottomSheetDialogFragment (его наследник) то пока BottomSheetDialog полностью не скроется
 *  будет происходить затемнение экрана (как при вызове диалогового окна)
 */

class SelectSortBottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    companion object {
        fun newInstance(): SelectSortBottomSheetFragment {
            return SelectSortBottomSheetFragment()
        }
    }


   private lateinit var behavior:  BottomSheetBehavior<View?>

    override fun onClick(v: View?) {
        Log.d("logFragment", "SelectSortBottomSheetFragment onClick")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_sort_bottom_sheet, container, false)
    }

    override fun onStart() {
        super.onStart()
        behavior = BottomSheetBehavior.from(bottomSheet.parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = 50
        behavior.isHideable = false
        behavior.skipCollapsed = true

    }
}