package dev.wirespec.hopeforpaws.ui.pets

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.wirespec.hopeforpaws.da.paging.PetsPagingDataSource
import dev.wirespec.hopeforpaws.da.web.PetAPIOptions
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import kotlinx.coroutines.flow.Flow

class PetsViewModel : ViewModel() {

    private val apiOptions = PetAPIOptions()

    val pets: Flow<PagingData<PetListItemInfo>> = Pager(PagingConfig(pageSize = 20)) {
        PetsPagingDataSource(apiOptions)
    }.flow

/*    private lateinit var _petsFlow: Flow<PagingData<PetListItemInfo>>
    private val apiOptions = PetAPIOptions()

    val pets: Flow<PagingData<PetListItemInfo>>
        get() = _petsFlow

    init {
        viewModelScope.launch {
            getPets()
        }
    }

    private suspend fun getPets() {
        _petsFlow = Repository.getPets(apiOptions).cachedIn(viewModelScope)
    }*/
}