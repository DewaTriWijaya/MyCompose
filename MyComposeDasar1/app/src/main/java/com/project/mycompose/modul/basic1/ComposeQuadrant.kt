package com.project.mycompose.modul.basic1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ComposeQuadrant() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Text Composable",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Displays text and follows Material Design guidelines.",
                    textAlign = TextAlign.Justify
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Image composable",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    textAlign = TextAlign.Justify,
                    text = "Creates a composable that lays out and draws a given Painter class object."
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Text Composable",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    textAlign = TextAlign.Justify,
                    text = "Displays text and follows Material Design guidelines."
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 16.dp)
            ) {
                Text(

                    text = "Image composable",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    textAlign = TextAlign.Justify,
                    text = "Creates a composable that lays out and draws a given Painter class object."
                )
            }
        }
    }

}