package com.example.machinetest.views.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.R
import com.example.machinetest.repository.models.PojoItem
import com.example.machinetest.views.utils.inflate
import kotlinx.android.synthetic.main.row_main_item.view.*

/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 */
class MainAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TAG = "Preferences"
    }

    private var itemList = mutableListOf<PojoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeFilterViewHolder(parent.inflate(R.layout.row_main_item))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeFilterViewHolder) {
            holder.bindFilters(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateItemList(itemList: List<PojoItem>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class HomeFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                //Invert Checkbox
                itemList[adapterPosition].isSelected = !itemList[adapterPosition].isSelected
                notifyDataSetChanged()
            }
        }

        fun bindFilters(pojoItem: PojoItem) {
            itemView.apply {
                //Assign values
                tvItem.text = pojoItem.value.toString()
                if (pojoItem.isSelected) ivChecked.visibility =
                    View.VISIBLE else ivChecked.visibility = View.GONE
            }
        }
    }

    interface MainAdapterCallbacks {
        fun onFilterChange(filterList: List<PojoItem>)
    }
}