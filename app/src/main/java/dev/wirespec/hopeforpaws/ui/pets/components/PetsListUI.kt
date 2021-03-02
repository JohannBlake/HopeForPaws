package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel
import dev.wirespec.hopeforpaws.utils.DeviceUtils
import kotlinx.coroutines.flow.Flow

@Composable
fun PetsListUI(pets: Flow<PagingData<PetListItemInfo>>, vm: PetsViewModel = viewModel()) {
    Row {
        val petItems = pets.collectAsLazyPagingItems()
        var colWidth = (DeviceUtils.screenRectDp.width() / 3).toInt()

        LazyColumn {
            items(petItems) { pet ->
                PetGridRowUI(petListItem = pet!!, petItems = petItems, colWidth = colWidth, onItemClick = { petClicked ->
                    vm.onGridItemClick(petClicked)
                })
            }
        }
    }
}