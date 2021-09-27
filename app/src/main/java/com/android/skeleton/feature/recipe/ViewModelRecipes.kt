package com.android.skeleton.feature.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.skeleton.di.FactoryRepository
import com.android.skeleton.domain.Recipe
import kotlinx.coroutines.launch

class ViewModelRecipes(application: Application): AndroidViewModel(application) {
    private val factoryRepository = FactoryRepository()
    private val repositoryRecipe = factoryRepository.getRepositoryRecipe(application)

    private val _dataStatus = MutableLiveData(DataLoadStatus.IN_PROGRESS)
    val dataStatus: LiveData<DataLoadStatus>
        get() = _dataStatus

    var recipes: List<Recipe> = emptyList()

    init {
        loadData()
    }

    /**
     * Triggers retrieval of the data, and updates [dataStatus].
     */
    fun loadData() {
        _dataStatus.value = DataLoadStatus.IN_PROGRESS
        viewModelScope.launch {
            try {
                recipes = repositoryRecipe.loadAll()
                _dataStatus.value = DataLoadStatus.SUCCESSFUL
            } catch (e: Exception) {
                _dataStatus.value = DataLoadStatus.FAILED
            }
        }
    }
}

enum class DataLoadStatus { IN_PROGRESS, SUCCESSFUL, FAILED }