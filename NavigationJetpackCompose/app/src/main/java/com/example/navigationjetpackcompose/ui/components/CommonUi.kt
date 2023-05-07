package com.example.navigationjetpackcompose.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.navigationjetpackcompose.R

@Composable
fun FormattedPriceLabel(subTotal: String, modifier: Modifier = Modifier){
    Text(
        text = stringResource(id = R.string.subtotal_price, subTotal),
        modifier = modifier
    )
}