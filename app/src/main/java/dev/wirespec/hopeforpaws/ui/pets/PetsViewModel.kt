package dev.wirespec.hopeforpaws.ui.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.wirespec.hopeforpaws.da.Repository
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PetsViewModel: ViewModel() {
    private lateinit var _petsFlow: Flow<PagingData<PetListItemInfo>>
    private val apiOptions = PetAPIOptions()

    val petsFlow: Flow<PagingData<PetListItemInfo>>
        get() = _petsFlow

    init {
        viewModelScope.launch {
            getPets()
        }
    }

    private suspend fun getPets() {
        _petsFlow = Repository.getPets(apiOptions).cachedIn(viewModelScope)
    }

/*    private fun getPets() = launchPagingAsync({
        Repository.getPets(apiOptions).cachedIn(viewModelScope)
    }, {
        _petsFlow = it
    })*/
}