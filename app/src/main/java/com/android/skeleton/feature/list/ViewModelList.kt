package com.android.skeleton.feature.list

import android.content.Context
import android.view.View
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import com.android.skeleton.di.FactoryRepository
import com.android.skeleton.domain.Item
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.android.skeleton.feature.FragmentHeader
import timber.log.Timber

/**
 * Supports fragments [FragmentHeader], [FragmentList].
 */
@HiltViewModel
class ViewModelList @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    private val factoryRepository = FactoryRepository()
    private val repositoryItem = factoryRepository.getRepositoryItem(context)
    // consider where this restriction should be defined in your app
    private val maxListSize = 10

    val items = repositoryItem.loadAll()
    val size = Transformations.map(items) {
        it.size
    }

    // size restriction in a simplistic way
    // you may want to add this kind of checks in your api
    val fabVisibility = Transformations.map(repositoryItem.size()) {size->
        when (size < maxListSize) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    private val _signalToItem = MutableLiveData<ItemData?>(null)
    val signalToItem: LiveData<ItemData?>
        get() = _signalToItem
    /**
     * Signals for the creation of an [Item].
     */
    fun createItem() {
        viewModelScope.launch {
            _signalToItem.value = ItemData(true, repositoryItem.new())
        }
    }

    /**
     * Signals for the editing of [item].
     */
    fun editItem(item: Item) {
        _signalToItem.value = ItemData(false, item)
    }

    fun resetSignalToItem() {
        _signalToItem.value = null
    }

    /**
     * Deletes [item].
     */
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repositoryItem.delete(item)
        }
    }

    /**
     * Sets position to [from] for the [Item] with position = [to].
     * Sets position to [to] for the [Item] with position = [from].
     */
    fun swapItems(from: Int, to: Int) {
        Timber.d("swapItems")
        viewModelScope.launch {
            Timber.d("swapItemsLaunch")
            val itemFrom = repositoryItem.getItemAt(from)
            val itemTo = repositoryItem.getItemAt(to)
            repositoryItem.save(listOf(
                Item(itemFrom.name, itemFrom.description, to),
                Item(itemTo.name, itemTo.description, from)
            ))
            Timber.d("swapItemsLaunchFinish")
        }
    }

    data class ItemData(val isNew: Boolean, val item: Item)
}