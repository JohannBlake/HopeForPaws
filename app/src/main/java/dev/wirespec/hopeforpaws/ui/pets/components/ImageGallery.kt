package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.wirespec.hopeforpaws.da.web.PETS_LARGE_IMAGES_PATH
import dev.wirespec.hopeforpaws.da.web.PETS_THUMBNAIL_IMAGES_PATH
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.theme.LightGray
import dev.wirespec.hopeforpaws.ui.theme.WhiteAlpha

@Composable
fun ImageGallery(pet: PetListItemInfo) {
    var selectedThumbnailNumber by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(10.dp))
            .verticalScroll(rememberScrollState())
            .background(WhiteAlpha)
    ) {
        CoilImage(
            data = PETS_LARGE_IMAGES_PATH + pet.id + "-" + selectedThumbnailNumber + ".jpg",
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(400.dp),
            fadeIn = true,
            contentScale = ContentScale.Crop
        )

        if (pet.imageCount > 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(140.dp)
                    .padding(top = 4.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                for (i in 1 until pet.imageCount + 1) {
                    Column(
                        modifier = Modifier
                            .requiredWidth(94.dp)
                            .requiredHeight(140.dp)
                    ) {

                        CoilImage(
                            data = PETS_THUMBNAIL_IMAGES_PATH + pet.id + "-" + i + ".jpg",
                            contentDescription = "",
                            modifier = Modifier
                                .width(90.dp)
                                .height(90.dp)
                                .clickable {
                                    selectedThumbnailNumber = i
                                },
                            fadeIn = true,
                            contentScale = ContentScale.Fit
                        )

                        val modifier = Modifier
                            .height(5.dp)
                            .fillMaxWidth()
                            .padding(end = 4.dp, top = 4.dp)

                        if (i == selectedThumbnailNumber) {
                            Surface(modifier = modifier) {
                                Row(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Cyan)) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}