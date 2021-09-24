package com.android.skeleton.feature

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.skeleton.di.FactoryRepository
import com.android.skeleton.domain.Item
import kotlinx.coroutines.launch

/**
 * Supports [FragmentItem] in the creation and editing of [Item]s.
 */
class ViewModelItem(application: Application, isNew: Boolean, val item: Item) :
    AndroidViewModel(application) {
    private val factoryRepository = FactoryRepository()
    private val repositoryItem = factoryRepository.getRepositoryItem(application)

    // existing items are not allowed to change name
    val isNameEditable = isNew

    // to signal fragment to dismiss itself
    private var _signalDismiss = MutableLiveData(false)
    val signalDismiss: LiveData<Boolean>
        get() = _signalDismiss

    /**
     * Saves an [Item] using [name], [description].
     */
    fun save(name: Editable?, description: Editable?) {
        // validate user input if necessary
        val nameString = name?.toString() ?: ""
        val descriptionString = description?.toString() ?: ""
        viewModelScope.launch {
            repositoryItem.save(Item(nameString, descriptionString, item.position))
        }
        dismiss()
    }

    /**
     * Aborts the creation or editing.
     */
    fun cancel() {
        dismiss()
    }

    /**
     * Triggers navigation back, out of this fragment.
     */
    private fun dismiss() {
        _signalDismiss.value = true
    }

    fun resetSignalDismiss() {
        _signalDismiss.value = false
    }
}