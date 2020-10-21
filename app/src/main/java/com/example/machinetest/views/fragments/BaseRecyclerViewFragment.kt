package com.example.machinetest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.machinetest.views.utils.inflate
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 *
 * @description extend this class for fragment setup with 1 recyclerView
 */
abstract class BaseRecyclerViewFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(layoutRes = layoutId)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set RecyclerView
        recyclerView.layoutManager =
            if (null == layoutManager) androidx.recyclerview.widget.LinearLayoutManager(activity) else (layoutManager)
        if (isShowRecyclerViewDivider) {

            // native divider is being used
            recyclerView.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    activity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
        }
        recyclerView.adapter = recyclerViewAdapter

        init()
    }

    /**
     *  @description call this method to when no data is found for recycler view to show some message
     *  @param message {String} String message to be shown (if null method will use resId to show text)
     *  @param resId {Int?} resource id is string (will be used if message value is null)
     */
    fun showNoDataText(resId: Int?, message: String? = null) {
        if (null == resId && null == message) {
            hideNoDataText()
        } else {
            tvNoData?.visibility = View.VISIBLE
            tvNoData?.text = message ?: getString(resId!!)
        }
    }

    /**
     *  @description call this method to hide NoDataText
     */
    fun hideNoDataText() {
        tvNoData?.visibility = View.GONE
    }

    abstract val layoutId: Int

    abstract fun init()

    abstract val recyclerViewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>?

    abstract val layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager?

    abstract val isShowRecyclerViewDivider: Boolean
}