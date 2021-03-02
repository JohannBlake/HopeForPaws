package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.wirespec.hopeforpaws.da.web.PETS_THUMBNAIL_IMAGES_PATH
import dev.wirespec.hopeforpaws.models.PetListItemInfo

@Composable
fun PetGridRowUI(
    petListItem: PetListItemInfo,
    petItems: LazyPagingItems<PetListItemInfo>,
    colWidth: Int,
    onItemClick: (PetListItemInfo) -> Unit
) {

    // If the item that is being requested appears in the first column, return it and all the
    // other items following it that make up the row.

    var pos = (petListItem.position + 1) % 3

    if (pos != 1)
        return

    var lastItemPos = petListItem.position + 2

    if (lastItemPos > petItems.itemCount - 1)
        lastItemPos = petItems.itemCount - 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(colWidth.dp)
            .padding(0.dp)

    ) {
        for (c in petListItem.position until lastItemPos + 1) {
            val pet = petItems.get(c)

            Box {
                CoilImage(
                    data = "$PETS_THUMBNAIL_IMAGES_PATH${pet!!.id}-1.jpg",
                    contentDescription = "",
                    modifier = Modifier
                        .requiredWidth(colWidth.dp)
                        .clickable {
                            onItemClick(pet)
                        },
                    fadeIn = true,
                    contentScale = ContentScale.Fit,

                    )
                Row(modifier = Modifier.padding(top = 110.dp)) {
                    Text(
                        pet.name, modifier = Modifier
                            .width(colWidth.dp)
                            .padding(start = 5.dp), color = Color.White
                    )
                }
            }
        }
    }
}
