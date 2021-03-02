package dev.wirespec.hopeforpaws.ui.pets.components

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.wirespec.hopeforpaws.R
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel
import dev.wirespec.hopeforpaws.ui.theme.LightCyan

@Composable
fun SplashScreenUI(vm: PetsViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalArrangement = Arrangement.Center
        ) {
            val image: Painter = painterResource(id = R.drawable.app_icon)
            Image(
                painter = image, contentDescription = "", modifier = Modifier
                    .requiredWidth(75.dp)
                    .requiredHeight(75.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.app_name))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(modifier = Modifier.requiredWidth(110.dp), color = LightCyan)
        }
    }

    Handler().postDelayed({
        vm.onSplashScreenCompleted()
    }, 4000)
}