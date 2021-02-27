package dev.wirespec.hopeforpaws.ui.pets

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.theme.HopeForPawsTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : AppCompatActivity() {
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

@Composable
fun PetsObs(vm: PetsViewModel = viewModel()) {
    PetsUI(vm.pets)
}

@Composable
fun PetsUI(pets: Flow<PagingData<PetListItemInfo>>) {
    val petItems = pets.collectAsLazyPagingItems()

    LazyColumn {

        items(petItems) { pet ->

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HopeForPawsTheme {
        PetsObs()
    }
}