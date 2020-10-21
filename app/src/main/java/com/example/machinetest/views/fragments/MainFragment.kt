package com.example.machinetest.views.fragments

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.R
import com.example.machinetest.repository.models.PojoItem
import com.example.machinetest.views.adapters.MainAdapter

/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 */
class MainFragment : BaseRecyclerViewFragment() {

    companion object {
        const val TAG = "MainFragment"
        private const val BUNDLE_EXTRAS_FRAGMENT_POSITION = "fragmentPosition"

        fun newInstance(fragmentPosition: Int): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_EXTRAS_FRAGMENT_POSITION, fragmentPosition)
                }
            }
        }
    }

    private val fragmentPosition by lazy { arguments!!.getInt(BUNDLE_EXTRAS_FRAGMENT_POSITION, 1) }
    private val adapter by lazy { MainAdapter() }

    override val layoutId: Int
        get() = R.layout.fragment_main

    override val recyclerViewAdapter: RecyclerView.Adapter<*>?
        get() = adapter

    override val layoutManager: RecyclerView.LayoutManager?
        get() = null

    override val isShowRecyclerViewDivider: Boolean
        get() = true

    override fun init() {
        val itemList = mutableListOf<PojoItem>()
        itemList.add(PojoItem(1))
        itemList.add(PojoItem(2))
        itemList.add(PojoItem(3))
        itemList.add(PojoItem(4))
        itemList.add(PojoItem(5))
        itemList.add(PojoItem(6))
        itemList.add(PojoItem(7))
        adapter.updateItemList(itemList)
    }

}