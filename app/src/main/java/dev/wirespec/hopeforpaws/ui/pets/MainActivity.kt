package dev.wirespec.hopeforpaws.ui.pets

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.wirespec.hopeforpaws.ui.pets.components.PetsMainUI
import dev.wirespec.hopeforpaws.ui.theme.HopeForPawsTheme


class MainActivity : AppCompatActivity() {
    lateinit var vm: PetsViewModel

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HopeForPawsTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    PetsObs()
                }
            }
        }
    }

    override fun onBackPressed() {
        val viewModel by viewModels<PetsViewModel>()

        if (!viewModel.onBackButtonPressed())
            super.onBackPressed()
    }
}


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun PetsObs(vm: PetsViewModel = viewModel()) {
    var screen = vm.screen.observeAsState(Screens.SPLASH_SCREEN)
    var selectedPet = vm.selectedPetItem.observeAsState(null)

    PetsMainUI(screen.value, pets = vm.pets, selectedPet = selectedPet.value)
}



