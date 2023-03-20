package com.project.mycompose.modul.basic3

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.mycompose.R
import com.project.mycompose.modul.basic3.data.DataSource
import com.project.mycompose.modul.basic3.model.Dog
import com.project.mycompose.ui.theme.Green50
import com.project.mycompose.ui.theme.MyComposeTheme

@Preview
@Composable
fun WoofApp() {
    MyComposeTheme(darkTheme = true) {
        WoofCompose()
    }
}

@Composable
fun WoofCompose() {
    Scaffold(
        topBar = { WoofTopBar() },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(padding)
            ) {
                items(DataSource().dogs) {
                    DogItem(dog = it)
                }
            }
        })
}

@Composable
fun WoofTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null,
            modifier = modifier
                .size(64.dp)
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (expanded) Green50 else MaterialTheme.colors.surface
    )

    Card(
        modifier = modifier
            .padding(all = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                //.background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
            ) {
                DogIcon(dogIcon = dog.imageResource)
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if(expanded) {
                DogHobby(dogHobby = dog.hobbies)
            }
        }
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(
                id = R.string.expand_button_content_description
            )
        )
    }
}

@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = stringResource(id = dogHobby),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = dogIcon),
        contentDescription = null,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(id = dogName),
            style = MaterialTheme.typography.h2,
            modifier = modifier
                .padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.years_old, dogAge),
            style = MaterialTheme.typography.body1
        )
    }
}

//@Preview
//@Composable
//fun WoofPreview() {
//    MyComposeTheme(darkTheme = false) {
//        WoofApp()
//    }
//}
