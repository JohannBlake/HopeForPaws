package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.wirespec.hopeforpaws.da.web.PETS_LARGE_IMAGES_PATH
import dev.wirespec.hopeforpaws.da.web.PETS_THUMBNAIL_IMAGES_PATH
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel

@Composable
fun PetDetailsUI(vm: PetsViewModel = viewModel()) {
    val pet = vm.selectedPetItem.value as PetListItemInfo

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.padding(10.dp)) {
            Card(
                border = BorderStroke(2.dp, Color.Red),
                backgroundColor = Color.Yellow,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .wrapContentHeight()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CoilImage(
                        data = "$PETS_LARGE_IMAGES_PATH${pet.id}-1.jpg",
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                        fadeIn = true,
                        contentScale = ContentScale.Crop
                    )

                    if (pet.imageCount > 1) {
                        Row(modifier = Modifier.fillMaxWidth().requiredHeight(90.dp).padding(top = 2.dp).horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            for (i in 1 until pet.imageCount + 1) {
                                CoilImage(
                                    data = PETS_THUMBNAIL_IMAGES_PATH + pet.id + "-" + i + ".jpg",
                                    contentDescription = "",
                                    modifier = Modifier.requiredWidth(90.dp),
                                    fadeIn = true,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }


            }
        }
    }
}