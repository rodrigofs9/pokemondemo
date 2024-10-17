package com.pokemondemo.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pokemondemo.R
import com.pokemondemo.presentation.theme.PokemonDemoTheme

@Composable
fun DetailsScreen(
    state: DetailsScreenState
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(115.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = state.name, fontSize = 14.sp)
                    }
                }
            }
            AsyncImage(
                model = state.imageUrl,
                contentDescription = "imagem do pokémon ${state.name}",
                Modifier
                    .size(130.dp)
                    .align(Alignment.TopEnd)
                    .offset(y = (-20).dp),
                placeholder = painterResource(id = R.drawable.bulbasaur),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PokemonDemoTheme {
        Surface {
            DetailsScreen(state = DetailsScreenState())
        }
    }
}
