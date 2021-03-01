package dev.wirespec.hopeforpaws.ui.pets

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
}

@ExperimentalFoundationApi
@Composable
fun PetsObs(vm: PetsViewModel = viewModel()) {
    PetsUI(vm.pets)
}

@ExperimentalFoundationApi
@Composable
fun PetsUI(pets: Flow<PagingData<PetListItemInfo>>) {

    val petItems = pets.collectAsLazyPagingItems()
    var colWidth = (screenRectDp.width() / 3  ).toInt()

    LazyColumn(
        //verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(petItems) { pet ->
            Log.i("HopeForPawsGrid", pet!!.name)
            PetGridRow(pet, petItems, colWidth)
        }
    }
}

@Composable
fun PetGridRow(petListItem: PetListItemInfo, petItems: LazyPagingItems<PetListItemInfo>, colWidth: Int) {
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
                    data = "https://storage.googleapis.com/wirespec.appspot.com/images/cats/" + pet!!.id + "-1.jpg",
                    contentDescription = "",
                    modifier = Modifier.requiredWidth(colWidth.dp),
                    fadeIn = true,
                    contentScale = ContentScale.Fit
                )
                Row(modifier = Modifier.padding(top = 110.dp)) {
                    Text(pet.name, modifier = Modifier.width(colWidth.dp).padding(start = 5.dp), color = Color.White)
                }
            }
        }
    }
}
