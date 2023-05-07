package com.example.navigationjetpackcompose.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationjetpackcompose.R
import com.example.navigationjetpackcompose.data.DataSource.quantityOptions

// Composable that allows the user to select the desired cupcake quantity and expects
// [onNextButtonClicked] lambda that expects the selected quantity and triggers the navigation to next screen
@Composable
fun StartOrderScreen(
    quantityOptions: List<Pair<Int, Int>>,
    // TODO: add onNextButtonClicked
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier.width(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.order_cupcakes),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(8.dp))
        quantityOptions.forEach { item ->
            SelectQuantityButton(
                labelResourceId = item.first,
                onClick = { /*handle next button*/ }
            )
        }
    }
}

// Customizable button composable that displays the [labelResourceId]
// and triggers [onClick] lambda when this composable is clicked
@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
       Text(stringResource(id = labelResourceId))
    }
}

@Preview
@Composable
fun StartOrderPreview(){
    StartOrderScreen(quantityOptions = quantityOptions)
}