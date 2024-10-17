package com.pokemondemo.presentation.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pokemondemo.R
import com.pokemondemo.domain.model.Pokemon
import com.pokemondemo.domain.model.PokemonType
import com.pokemondemo.presentation.theme.PokemonDemoTheme

@Composable
fun PokemonHomeListItem(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(115.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Column(
                    modifier = modifier.weight(1f)
                ) {
                    Text(text = pokemon.id, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = pokemon.name, fontSize = 14.sp)
                    Text(text = pokemon.types.first().name, fontSize = 14.sp)
                }
            }
        }
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = "imagem do pokémon ${pokemon.name}",
            modifier.size(130.dp)
                .align(Alignment.TopEnd)
                .offset(y = (-20).dp),
            placeholder = painterResource(id = R.drawable.bulbasaur),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun PokemonHomeListItemPreview() {
    PokemonDemoTheme {
        Surface {
            PokemonHomeListItem(
                pokemon = Pokemon(
                    id = "001",
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    types = listOf(PokemonType(name = "Grass"), PokemonType(name = "Poison"))
                ),
            )
        }
    }
}