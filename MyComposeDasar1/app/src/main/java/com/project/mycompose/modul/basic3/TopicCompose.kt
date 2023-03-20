package com.project.mycompose.modul.basic3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.mycompose.R
import com.project.mycompose.modul.basic3.data.DataSource
import com.project.mycompose.modul.basic3.model.Topic
import com.project.mycompose.ui.theme.MyComposeTheme


@Preview
@Composable
fun TopicApp(){
    MyComposeTheme {
        TopicList()
    }
}

@Composable
fun TopicList(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ){
        items(DataSource().topics) {
            TopicCard(topic = it)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        elevation = 4.dp
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = null,
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp),
            )
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = topic.title.toString(),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                    )
                    Text(
                        text = topic.count.toString(),
                        modifier = Modifier
                            .padding(start = 3.dp, top = 3.dp)
                    )
                }
            }
        }
    }
}