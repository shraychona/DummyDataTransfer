package com.example.machinetest.views.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.R
import com.example.machinetest.viewmodels.SharedViewModel
import com.example.machinetest.views.adapters.MainAdapter

/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 */
class MainFragment : BaseRecyclerViewFragment() {

    companion object {
        const val TAG = "MainFragment"
        private const val BUNDLE_EXTRAS_IS_FIRST_FRAGMENT = "isFirstFragment"

        fun newInstance(isFirstFragment: Boolean): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(BUNDLE_EXTRAS_IS_FIRST_FRAGMENT, isFirstFragment)
                }
            }
        }
    }

    private val isFirstFragment by lazy {
        arguments!!.getBoolean(
            BUNDLE_EXTRAS_IS_FIRST_FRAGMENT,
            true
        )
    }

    private val adapter by lazy { MainAdapter() }

    private val mSharedViewModel by lazy {
        //Getting ViewModel
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.fragment_main

    override val recyclerViewAdapter: RecyclerView.Adapter<*>?
        get() = adapter

    override val layoutManager: RecyclerView.LayoutManager?
        get() = null

    override val isShowRecyclerViewDivider: Boolean
        get() = true

    override fun init() {
        if (isFirstFragment) {
            mSharedViewModel.setupItemList()
        }

        observeProperties()
    }

    //Helper method to observe properties via ViewModel
    private fun observeProperties() {
        if (isFirstFragment)
            mSharedViewModel.getFistListLiveData().observe(viewLifecycleOwner, {
                it?.let {
                    adapter.updateItemList(it)
                    if (it.isEmpty())
                        showNoDataText(resId = R.string.text_no_data_in_list)
                    else hideNoDataText()
                }
            })
        else mSharedViewModel.getSecondListLiveData().observe(viewLifecycleOwner, {
            it?.let {
                adapter.updateItemList(it)
                if (it.isEmpty())
                    showNoDataText(resId = R.string.text_no_data_in_list)
                else hideNoDataText()
            }
        })
    }

    fun moveItems() {
        val selectedItems = adapter.getSelectedList()
        if (selectedItems.isEmpty())
            Toast.makeText(
                requireContext(),
                getString(R.string.text_no_item_selected),
                Toast.LENGTH_SHORT
            ).show()
        else
            mSharedViewModel.transferItems(isFirstFragment, selectedItems)
    }

}