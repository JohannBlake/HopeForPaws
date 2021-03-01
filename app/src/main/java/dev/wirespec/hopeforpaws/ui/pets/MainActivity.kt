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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.theme.HopeForPawsTheme
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

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(petItems) { pet ->
            Log.i("HopeForPawsGrid", pet!!.name)
            PetGridRow(pet, petItems)
        }
    }

/*    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(3) { index ->
            if (petItems.itemCount == 0)
                return@items

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 15.dp)
            ) {
                CoilImage(
                    data = "https://storage.googleapis.com/wirespec.appspot.com/images/cats/" + petItems[index]!!.id + "-1.jpg",
                    contentDescription = "Random cute yorkshire",
                    modifier = Modifier.fillMaxWidth(),
                    fadeIn = true
                    //modifier = Modifier.width(200.dp),
                    //contentScale = ContentScale.FillWidth
                )
            }
        }
    }*/
}

@Composable
fun PetGridRow(pet: PetListItemInfo, petItems: LazyPagingItems<PetListItemInfo>) {
    // If the item that is being requested appears in the first column, return it and all the
    // other items following it that make up the row.

    var pos = (pet.position + 1) % 3

    if (pos != 1)
        return

    var lastItemPos = pet.position + 2

    if (lastItemPos > petItems.itemCount - 1)
        lastItemPos = petItems.itemCount - 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(0.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp)

    ) {
        for (c in pet.position until lastItemPos + 1) {
            CoilImage(
                data = "https://storage.googleapis.com/wirespec.appspot.com/images/cats/" + petItems.get(c)!!.id + "-1.jpg",
                contentDescription = "",
                modifier = Modifier.height(120.dp),
                fadeIn = false,
                //modifier = Modifier.width(200.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HopeForPawsTheme {
        //PetsObs()
    }
}