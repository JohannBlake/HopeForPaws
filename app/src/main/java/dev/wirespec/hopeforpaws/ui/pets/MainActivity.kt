package dev.wirespec.hopeforpaws.ui.pets

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.wirespec.hopeforpaws.da.web.PETS_THUMBNAIL_IMAGES_PATH
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.theme.HopeForPawsTheme
import dev.wirespec.hopeforpaws.utils.DeviceUtils.Companion.screenRectDp
import kotlinx.coroutines.flow.Flow


class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HopeForPawsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PetsObs()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

@ExperimentalFoundationApi
@Composable
fun PetsObs(vm: PetsViewModel = viewModel()) {
    var screen = vm.screen.observeAsState(Screens.PET_LIST)
    var selectedPet = vm.selectedPetItem.observeAsState(null)

    PetsUI(screen.value, pets = vm.pets, selectedPet = selectedPet.value)
}

@ExperimentalFoundationApi
@Composable
fun PetsUI(screen: Screens, pets: Flow<PagingData<PetListItemInfo>>, vm: PetsViewModel = viewModel(), selectedPet: PetListItemInfo?) {
    when (screen) {
        Screens.PET_LIST -> {
            Row {
                val petItems = pets.collectAsLazyPagingItems()
                var colWidth = (screenRectDp.width() / 3  ).toInt()

                LazyColumn {
                    items(petItems) { pet ->
                        PetGridRow(petListItem = pet!!, petItems = petItems, colWidth = colWidth, onItemClick = {petClicked ->
                            vm.onGridItemClick(petClicked)
                            //screen = Screens.PET_DETAILS
                        })
                    }
                }
            }
        }
        Screens.PET_DETAILS -> {
            Text("Details go here")
        }
    }
}
//onItemClick: (PetListItemInfo) -> Unit
@Composable
fun PetGridRow(
    petListItem: PetListItemInfo,
    petItems: LazyPagingItems<PetListItemInfo>,
    colWidth: Int,
    onItemClick: (PetListItemInfo) -> Unit) {

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
                    Text(pet.name, modifier = Modifier
                        .width(colWidth.dp)
                        .padding(start = 5.dp), color = Color.White)
                }
            }
        }
    }
}
