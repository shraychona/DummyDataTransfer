package com.example.machinetest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.machinetest.repository.models.PojoItem

/**
 * @author shraychona@gmail.com
 * @since 21 Oct 2020
 *
 * @param application is passed in order to subscribe to the android life cycle.
 */
class SharedViewModel(application: Application) : AndroidViewModel(application) {

    //variables
    private var firstItemList = MutableLiveData<List<PojoItem>>()
    private var secondItemList = MutableLiveData<List<PojoItem>>()
    private val firstList = mutableListOf<PojoItem>()
    private val secondList = mutableListOf<PojoItem>()

    fun setupItemList() {
        firstList.add(PojoItem(0))
        firstList.add(PojoItem(1))
        firstList.add(PojoItem(2))
        firstList.add(PojoItem(3))
        firstList.add(PojoItem(4))
        firstList.add(PojoItem(5))
        firstList.add(PojoItem(6))
        firstList.add(PojoItem(7))
        firstList.add(PojoItem(8))
        firstList.add(PojoItem(9))
        firstItemList.postValue(firstList)
    }

    fun transferItems(isFromLeftToRight: Boolean, itemList: List<PojoItem>) {
        itemList.forEach { it.isSelected = false }

        if (isFromLeftToRight) {
//            firstList.minus(itemList)
//            secondList.plus(itemList)
            firstList.removeAll { it in itemList }
            secondList.addAll(itemList)
        } else {
//            firstList.plus(itemList)
//            secondList.minus(itemList)
            firstList.addAll(itemList)
            secondList.removeAll { it in itemList }
        }
        firstItemList.postValue(firstList)
        secondItemList.postValue(secondList)
    }

    //LiveData to observe in the Fragment using ViewModel
    fun getFistListLiveData(): LiveData<List<PojoItem>> = firstItemList
    fun getSecondListLiveData(): LiveData<List<PojoItem>> = secondItemList
}