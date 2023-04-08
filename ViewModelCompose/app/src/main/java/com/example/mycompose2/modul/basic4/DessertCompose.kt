package com.example.mycompose2.modul.basic4

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import com.example.mycompose2.ui.theme.MyCompose2Theme


// Determine which dessert to show
fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            break
        }
    }
    return dessertToShow
}

// Share dessert sold information using ACTION_SEND intent
private fun shareSoldDessertsInformation(
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
fun DessertClickerApp(
    desserts: List<Dessert>
){
    //var revenue by remember { mutableStateOf(0) }
    var revenue by rememberSaveable { mutableStateOf(0) }
    
    var dessertsSold by remember { mutableStateOf(0) }

    val currentDessertIndex by remember { mutableStateOf(0) }

    var currentDessertPrice by remember {
        mutableStateOf(desserts[currentDessertIndex].price)
    }
//    var currentDessertImageId by remember {
//        mutableStateOf(desserts[currentDessertIndex].imageId)
//    }
    var currentDessertImageId by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].imageId)
    }

    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            AppBar(
                onShareButtonClicked = {
                    shareSoldDessertsInformation(
                        intentContext = intentContext,
                        dessertSold = dessertsSold,
                        revenue = revenue
                    )
                }
            )
        }
    ) { contentPadding ->
        DessertClickerScreen(
            revenue = revenue,
            dessertSold = dessertsSold,
            dessertImageId = currentDessertImageId,
            onDessertClicked = {

                // Update the revenue
                revenue += currentDessertPrice
                dessertsSold++

                // Show the next dessert
                val desserToShow = determineDessertToShow(desserts, dessertsSold)
                currentDessertImageId = desserToShow.imageId
                currentDessertPrice = desserToShow.price
            },
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun AppBar(
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
fun DessertClickerScreen(
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
            TransactionInfo(revenue = revenue, dessertSold = dessertSold)
        }
    }
}

@Composable
fun TransactionInfo(
    revenue: Int,
    dessertSold: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .background(Color.White),
    ) {
        DessertSoldInfo(dessertSold = dessertSold)
        RevenueInfo(revenue = revenue)
    }
}

@Composable
private fun DessertSoldInfo(dessertSold: Int, modifier: Modifier = Modifier) {
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
fun RevenueInfo(revenue: Int, modifier: Modifier = Modifier) {
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
fun MyDessertClickerAppPreview(){
    MyCompose2Theme {
        DessertClickerApp(desserts = listOf(Dessert(R.drawable.cupcake, 5, 0)))
    }
}