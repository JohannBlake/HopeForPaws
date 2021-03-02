package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel

@Composable
fun PetDetailsUI(vm: PetsViewModel = viewModel()) {
    val pet = vm.selectedPetItem.value as PetListItemInfo

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Top, modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        ) {
            ImageGallery(pet)

/*            Card(
                border = BorderStroke(2.dp, Color.Red),
                backgroundColor = Color.Yellow,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .fillMaxHeight()
            ) {
                ImageGallery(pet)
            }*/
        }
    }
}