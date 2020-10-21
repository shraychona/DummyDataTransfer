package com.example.machinetest.views.activities

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.example.machinetest.R
import com.example.machinetest.views.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 */
class MainActivity : AppCompatActivity() {

    // variables
    private val firstFragment = MainFragment.newInstance(true)
    private val secondFragment = MainFragment.newInstance(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doFragmentTransaction(R.id.flFirstFragment, firstFragment, MainFragment.TAG)
        doFragmentTransaction(R.id.flSecondFragment, secondFragment, MainFragment.TAG)

        setupListeners()
    }

    private fun setupListeners() {
        btnMoveRight.setOnClickListener { firstFragment.moveItems() }
        btnMoveLeft.setOnClickListener { secondFragment.moveItems() }
    }

    private fun doFragmentTransaction(
        @IdRes containerViewId: Int,
        fragment: androidx.fragment.app.Fragment,
        tag: String = ""
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment, tag)
        fragmentTransaction.commit()
    }
}