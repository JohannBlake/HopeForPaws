package dev.wirespec.hopeforpaws.ui.pets

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
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
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(petItems) { pet ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 15.dp)
            ) {
                CoilImage(
                    data = "https://storage.googleapis.com/wirespec.appspot.com/images/cats/" + pet!!.id + "-1.jpg",
                    contentDescription = "Random cute yorkshire",
                    modifier = Modifier.fillMaxWidth(),
                    fadeIn = true
                    //modifier = Modifier.width(200.dp),
                    //contentScale = ContentScale.FillWidth
                )
            }
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