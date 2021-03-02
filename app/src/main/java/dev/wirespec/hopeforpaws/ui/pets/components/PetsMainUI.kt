package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel
import dev.wirespec.hopeforpaws.ui.pets.Screens
import kotlinx.coroutines.flow.Flow

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun PetsMainUI(screen: Screens, pets: Flow<PagingData<PetListItemInfo>>, vm: PetsViewModel = viewModel(), selectedPet: PetListItemInfo?) {
    PetsListUI(pets)
    AnimatedVisibility(
        visible = screen == Screens.PET_DETAILS,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it })
    ) {
        PetDetailsUI()
    }
}