package com.lopatin.sorts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lopatin.sorts.R
import com.lopatin.sorts.model.Sort
import kotlinx.android.synthetic.main.item_sort.view.*

class SelectSortAdapter(private val sortList: ArrayList<Sort>, private val selectSortListener: SelectSortListener) :
    RecyclerView.Adapter<SelectSortAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sort, parent, false)
        return Holder(view)

    }

    override fun getItemCount(): Int = sortList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.sortView.sortName.text = sortList[position].sortName
        holder.sortView.setOnClickListener {
            selectSortListener.selectSort(sortList[position])
        }
    }

    class Holder(val sortView: View) : RecyclerView.ViewHolder(sortView)

    interface SelectSortListener {
        fun selectSort(sort: Sort)
    }
}