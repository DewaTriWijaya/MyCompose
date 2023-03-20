package com.project.mycompose.modul.basic3

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.mycompose.modul.basic3.data.DataSource
import com.project.mycompose.modul.basic3.model.Day
import com.project.mycompose.ui.theme.Typography


@Composable
fun DayList() {
    Scaffold(
        topBar = { DayTopBar() },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(padding)
            ) {
                items(DataSource().days) {
                    DayCard(day = it)
                }
            }
        })
}

@Composable
fun DayTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .background(color = MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "30 Day Go TO International",
            style = Typography.h1
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(
                text = stringResource(day.day),
                style = Typography.h1
            )
            Text(
                text = stringResource(day.city),
                style = Typography.h2
            )
            Image(
                painter = painterResource(day.image),
                contentDescription = null
            )
            if (expanded) {
                DescriptionItem(day.description)
            }
        }
    }
}

@Composable
fun DescriptionItem(desc: Int){
    Text(
        text = stringResource(desc),
        style = Typography.body1
    )
}

@Preview
@Composable
fun DayPreview() {
    DayList()
}