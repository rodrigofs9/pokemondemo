package com.marveldemo.presentation.heroes

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marveldemo.R
import com.marveldemo.domain.model.Hero
import com.marveldemo.presentation.theme.HeroDemoTheme

@Composable
fun HeroesListItem(hero: Hero, modifier: Modifier = Modifier) {
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
                    Text(text = hero.id.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = hero.name, fontSize = 14.sp)
                    Text(
                        text = hero.description,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        AsyncImage(
            model = hero.imageUrl,
            contentDescription = "imagem do ${hero.name}",
            modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .offset(y = (-20).dp),
            placeholder = painterResource(id = R.drawable.bulbasaur),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun HeroesListItemPreview() {
    HeroDemoTheme {
        Surface {
            HeroesListItem(
                hero = Hero(
                    id = 1,
                    name = "Spider Man",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    description = "Description"
                ),
            )
        }
    }
}