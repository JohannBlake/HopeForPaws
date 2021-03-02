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

    private val _screen = MutableLiveData(Screens.SPLASH_SCREEN)
    val screen: LiveData<Screens> = _screen

    private val _petItem = MutableLiveData<PetListItemInfo>(null)
    val selectedPetItem: LiveData<PetListItemInfo> = _petItem

    fun onGridItemClick(petItemClicked: PetListItemInfo) {
        _petItem.value = petItemClicked
        _screen.value = Screens.PET_DETAILS
    }

    fun onBackButtonPressed(): Boolean {
        if (screen.value == Screens.PET_DETAILS) {
            _screen.value = Screens.PET_LIST
            return true
        }

        return false
    }

    fun onSplashScreenCompleted() {
        _screen.value = Screens.PET_LIST
    }

    val pets: Flow<PagingData<PetListItemInfo>> = Pager(PagingConfig(pageSize = 20)) {
        PetsPagingDataSource(apiOptions)
    }.flow
}

enum class Screens {
    SPLASH_SCREEN,
    PET_LIST,
    PET_DETAILS
}