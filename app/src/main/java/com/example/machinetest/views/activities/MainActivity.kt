package com.example.machinetest.views.activities

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.example.machinetest.R
import com.example.machinetest.views.fragments.MainFragment


/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doFragmentTransaction(R.id.flFirstFragment, MainFragment.newInstance(1), MainFragment.TAG)
        doFragmentTransaction(R.id.flSecondFragment, MainFragment.newInstance(2), MainFragment.TAG)
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