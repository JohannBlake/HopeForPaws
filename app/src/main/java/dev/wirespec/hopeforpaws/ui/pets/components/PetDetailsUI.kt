package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PetDetailsUI() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.padding(10.dp)) {
            Card(
                border = BorderStroke(2.dp, Color.Red),
                backgroundColor = Color.Yellow,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .wrapContentHeight()
            ) {

                Text("Details go here")
            }
        }
    }
}