package dev.wirespec.hopeforpaws.ui.pets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _petItem = MutableLiveData<PetListItemInfo>(null)
    val petItem: LiveData<PetListItemInfo> = _petItem

    fun onGridItemClick(petItemClicked: PetListItemInfo) {
        _petItem.value = petItemClicked
    }

    val pets: Flow<PagingData<PetListItemInfo>> = Pager(PagingConfig(pageSize = 20)) {
        PetsPagingDataSource(apiOptions)
    }.flow
}