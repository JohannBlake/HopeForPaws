package dev.wirespec.hopeforpaws.ui.pets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
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
import dev.wirespec.hopeforpaws.R
import dev.wirespec.hopeforpaws.models.PetListItemInfo
import dev.wirespec.hopeforpaws.ui.pets.PetsViewModel
import dev.wirespec.hopeforpaws.ui.theme.LightGray
import dev.wirespec.hopeforpaws.ui.theme.WhiteAlpha


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
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Column(modifier =  Modifier.padding(start = 10.dp, end= 10.dp, bottom = 10.dp)) {
                    Text(stringResource(R.string.gender), style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.secondary)
                    Text(text = if (pet.gender == "m") stringResource(R.string.male) else stringResource(R.string.female))
                }
                Column(modifier =  Modifier.padding(start = 10.dp, end= 10.dp, bottom = 10.dp)) {
                    Text(stringResource(R.string.born), style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.secondary)
                    Text(text = pet.birthdate)
                }
                Column(modifier =  Modifier.padding(start = 10.dp, end= 10.dp, bottom = 10.dp)) {
                    Text(stringResource(R.string.color), style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.secondary)
                    Text(text = pet.color)
                }
                Column(modifier =  Modifier.padding(start = 10.dp, end= 10.dp, bottom = 10.dp)) {
                    Text(stringResource(R.string.type), style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.secondary)
                    Text(text = pet.type)
                }
            }
        }
    }
}