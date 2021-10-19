package com.android.skeleton.feature.item

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.skeleton.domain.Item

class ViewModelItemFactory(
    private val application: Application,
    private val isNew: Boolean,
    private val item: Item
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelItem::class.java)) {
            return ViewModelItem(application, isNew, item) as T
        }
        throw IllegalArgumentException("Unsupported view model class.")
    }
}