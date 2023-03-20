package com.project.mycompose.modul.basic2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.mycompose.R


@Preview(showBackground = true)
@Composable
fun ScreenDesignUI() {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (currentStep) {
            1 -> {
                ScreenProgram(
                    image = R.drawable.user_avatar_1,
                    title = "Seorang Programmer Mobile Android And IOS",
                    year = "4 Years (2019-2023)",
                    addClick = { currentStep = 2 },
                    minClick = { currentStep = 1 }
                )
            }
            2 -> {
                ScreenProgram(
                    image = R.drawable.user_avatar_2,
                    title = "Seorang UI/UX Di KitaBisa.com",
                    year = "3 Years (2020-2023)",
                    addClick = { currentStep = 3 },
                    minClick = { currentStep = 1 }
                )
            }
            3 -> {
                ScreenProgram(
                    image = R.drawable.user_avatar_3,
                    title = "Seorang Quality Assurance Di KitaBisa.com",
                    year = "2 Years (2020-2022)",
                    addClick = { currentStep = 3 },
                    minClick = { currentStep = 2 }
                )
            }
            else -> {

            }
        }
    }
}


@Composable
fun ScreenProgram(
    image: Int,
    title: String,
    year: String,
    addClick: () -> Unit,
    minClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Card(
            modifier = Modifier
                .padding(all = 16.dp)
                .wrapContentSize()
                .border(
                    BorderStroke(4.dp, Color.Black),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = modifier
                    .wrapContentSize()
                    .padding(all = 16.dp)
            )
        }

        Card(
            modifier = Modifier
                .padding(all = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = year,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = modifier
                .padding(16.dp),
        ) {
            Button(
                onClick = minClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .weight(1f)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = addClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }
}
