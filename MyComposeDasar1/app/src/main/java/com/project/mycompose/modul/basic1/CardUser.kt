package com.project.mycompose.modul.basic1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.mycompose.R

@Composable
fun CardUser(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF153C27))
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = null,
                modifier = modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = "Dewa Tri Wijaya",
                textAlign = TextAlign.Justify,
                fontSize = 40.sp,
                color = Color.White
            )
            Text(
                text = "Android Developer Extraordinaire",
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                color = Color(0xFF3ddc84)
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Divider(color = Color.White, thickness = 1.dp)
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 20.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
                )
                Text(
                    text = "+62 867 2497 2345",
                    color = Color.White,
                    modifier = modifier
                        .padding(top = 8.dp)
                )
            }
            Divider(color = Color.White, thickness = 1.dp)
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 20.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
                )
                Text(
                    text = "@AndroidDev",
                    color = Color.White,
                    modifier = modifier
                        .padding(top = 8.dp)
                )
            }
            Divider(color = Color.White, thickness = 1.dp)
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    modifier = modifier
                        .padding(start = 20.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
                )
                Text(
                    text = "dewatwc@android.com",
                    color = Color.White,
                    modifier = modifier
                        .padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}