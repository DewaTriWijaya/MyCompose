package com.project.mycompose.modul.basic3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.mycompose.modul.basic3.data.HeroesRepository
import com.project.mycompose.modul.basic3.model.Hero
import com.project.mycompose.ui.theme.MyComposeTheme



@Composable
fun HeroList(){
    Scaffold(
        topBar = { HeroTopBar() },
     content = { padding ->
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(padding)
        ){
            items(HeroesRepository().heroes) {
                HeroCard(it)
            }
        }
    })
}

@Composable
fun HeroTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Superheroes",
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .wrapContentHeight(),
        elevation = 2.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(8.dp)
        ) {
           Column(
               modifier = modifier
                   .padding(8.dp)
           ) {
               Text(
                   text = stringResource(hero.name),
                   style = MaterialTheme.typography.h2
               )
               Text(
                   text = stringResource(id = hero.name),
                   style = MaterialTheme.typography.body1,
               )
           } 
            Image(
                painter = painterResource(id = hero.image),
                contentDescription = null,
                modifier = modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(30))
            )
        }
    }
}


@Preview()
@Composable
fun HeroPreview() {
    MyComposeTheme(darkTheme = true) {
        HeroList()
    }
}