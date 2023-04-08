package com.example.mycompose2.modul.basic4

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.mycompose2.R
import com.example.mycompose2.modul.basic4.model.Dessert
import com.example.mycompose2.modul.basic4.model.DessertUiState
import com.example.mycompose2.modul.basic4.model.DessertViewModel
import com.example.mycompose2.ui.theme.MyCompose2Theme


// Share dessert sold information using ACTION_SEND intent
private fun shareSoldDessertsInformation1(
    intentContext: Context,
    dessertSold: Int,
    revenue: Int
){
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(R.string.share_text, dessertSold, revenue)
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)

    try {
        startActivity(intentContext, shareIntent, null)
    } catch (e: ActivityNotFoundException){
        Toast.makeText(
            intentContext,
            intentContext.getString(R.string.sharing_not_available),
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
private fun DessertClickerApp1(
    viewModel: DessertViewModel = viewModel()
) {
    val uiState by viewModel.iuState.collectAsState()
    DessertClickerApp1(
        uiState = uiState,
        onDessertClicked = viewModel::onDessertClicked
    )
}

@Composable
fun DessertClickerApp1(
    uiState: DessertUiState,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            AppBar1(
                onShareButtonClicked = {
                    shareSoldDessertsInformation1(
                        intentContext = intentContext,
                            dessertSold = uiState.dessertsSold,
                        revenue = uiState.revenue
                    )
                }
            )
        }
    ) { contentPadding ->
        DessertClickerScreen1(
            revenue = uiState.revenue,
            dessertSold = uiState.dessertsSold,
            dessertImageId = uiState.currentDessertImageId,
            onDessertClicked = onDessertClicked,
            modifier = modifier.padding(contentPadding)
        )
    }
}

@Composable
fun AppBar1(
    onShareButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6
        )
        IconButton(
            onClick = onShareButtonClicked,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share),
                tint = MaterialTheme.colors.onPrimary
            )
        }

    }
}

@Composable
fun DessertClickerScreen1(
    revenue: Int,
    dessertSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Image(
            painter = painterResource(id = R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = dessertImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .align(Alignment.Center)
                        .clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop
                )
            }
            TransactionInfo1(revenue = revenue, dessertSold = dessertSold)
        }
    }
}

@Composable
fun TransactionInfo1(
    revenue: Int,
    dessertSold: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .background(Color.White),
    ) {
        DessertSoldInfo1(dessertSold = dessertSold)
        RevenueInfo1(revenue = revenue)
    }
}

@Composable
private fun DessertSoldInfo1(dessertSold: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.dessert_sold),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = dessertSold.toString(),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun RevenueInfo1(revenue: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(
            text = stringResource(id = R.string.total_revenue),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "$${revenue}",
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h4
        )
    }
}


@Preview
@Composable
fun MyDessertClickerAppPreview1(){
    MyCompose2Theme {
        DessertClickerApp1()
    }
}