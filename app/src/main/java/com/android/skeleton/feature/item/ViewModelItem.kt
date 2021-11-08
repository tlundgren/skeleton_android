package com.android.skeleton.feature.item

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.skeleton.analytics.event.ItemOperation
import com.android.skeleton.di.FactoryAnalytics
import com.android.skeleton.di.FactoryCrash
import com.android.skeleton.di.FactoryRepository
import com.android.skeleton.domain.Item
import kotlinx.coroutines.launch

/**
 * Supports [FragmentItem] in the creation and editing of [Item]s.
 */
class ViewModelItem(application: Application, private val isNew: Boolean, val item: Item) :
    AndroidViewModel(application) {
    private val factoryRepository = FactoryRepository()
    private val repositoryItem = factoryRepository.getRepositoryItem(application)
    private val analyticsSender = FactoryAnalytics().getSender(application)
    private val crashContext = FactoryCrash().getContext(application)

    // existing items are not allowed to change name
    val isNameEditable = isNew

    // to signal fragment to dismiss itself
    private var _signalDismiss = MutableLiveData(false)
    val signalDismiss: LiveData<Boolean>
        get() = _signalDismiss

    init {
        // in the case of a crash, we will know if the item isNew
        crashContext.add(com.android.skeleton.crash.key.Item.IS_NEW.name, isNew)
    }

    /**
     * Saves an [Item] using [name], [description].
     */
    fun save(name: Editable?, description: Editable?) {
        // if a matching item already exists, we will simply overwrite it
        // you may want to add a warning to the user, or define a different logic
        // validate user input if necessary
        val nameString = name?.toString() ?: ""
        // if name contains crash, crash (this is to test crash logging)
        if (nameString.contains("crash", ignoreCase = true))
            throw RuntimeException("Forced crash ${nameString}")
        val descriptionString = description?.toString() ?: ""
        viewModelScope.launch {
            repositoryItem.save(Item(nameString, descriptionString, item.position))
            if (isNew) {
                analyticsSender.sendEvent(ItemOperation(ItemOperation.Operation.CREATE, nameString))
            }
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