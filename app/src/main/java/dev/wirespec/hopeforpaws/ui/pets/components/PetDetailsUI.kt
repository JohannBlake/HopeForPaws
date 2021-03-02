package dev.wirespec.hopeforpaws.ui.pets.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.wirespec.hopeforpaws.App
import dev.wirespec.hopeforpaws.R
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel
import dev.wirespec.hopeforpaws.ui.theme.*


@Composable
fun PetDetailsUI(vm: PetsViewModel = viewModel()) {
    val pet = vm.selectedPetItem.value as PetListItemInfo

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Top, modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(10.dp))
                .background(WhiteAlpha)
                .verticalScroll(rememberScrollState())
        ) {
            ImageGallery(pet)
            Text(
                pet.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 30.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                DetailProperty(R.string.gender, if (pet.gender == "m") stringResource(R.string.male) else stringResource(R.string.female))
                DetailProperty(R.string.born, pet.birthdate)
                DetailProperty(R.string.color, pet.color)
                //DetailProperty(R.string.type, pet.type)
            }
            MultiLineText(
                pet.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                color = LightCyan
            )
            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                val buttonColors = ButtonDefaults.buttonColors(backgroundColor = Teal700, contentColor = Teal100)

                Button(
                    colors = buttonColors,
                    elevation = ButtonDefaults.elevation(5.dp),
                    onClick = {
                        val uri = Uri.parse("https://www.shelterluv.com/matchme/adopt/BKNB/Cat")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        App.context.startActivity(intent)
                    }) {
                    Text(
                        text = stringResource(R.string.adopt) + " " + pet.name,
                        modifier = Modifier.padding(start = 10.dp, top = 7.dp, end = 10.dp, bottom = 7.dp)
                    )
                }
            }
        }
    }
}