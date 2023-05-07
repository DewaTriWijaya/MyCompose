package com.example.navigationjetpackcompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationjetpackcompose.R
import com.example.navigationjetpackcompose.data.OrderUiState
import com.example.navigationjetpackcompose.ui.components.FormattedPriceLabel

// this composable expects [orderUiState] that represents the order state, [onCancelButtonClicked] lambda
// that triggers cancelling the order and passes the final order to [onSendButtonClicked] lambda

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    // TODO: add onCancelButtonClicked
    // TODO: add onSendButtonClicked
    modifier: Modifier = Modifier
){
    val resources = LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )
    // Load and format a string resource with the parameters.
    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrder = stringResource(id = R.string.new_cupcake_order)
    // Create a list of order summary to display
    val items = listOf(
        // Summary line 1: display selected quantity
        Pair(stringResource(id = R.string.quantity), numberOfCupcakes),
        // Summary line 2: display selected flavor
        Pair(stringResource(id = R.string.flavor), orderUiState.flavor),
        // Summary line 3: display selected pickup date
        Pair(stringResource(id = R.string.pickup_date), orderUiState.date)
    )

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            Text(text = item.first.uppercase())
            Text(text = item.second, fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormattedPriceLabel(
            subTotal = orderUiState.price,
            modifier = Modifier.align(Alignment.End)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO: handle send button*/ }
        ) {
            Text(text = stringResource(id = R.string.send))
        }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryPreview(){
    OrderSummaryScreen(orderUiState = OrderUiState(0, "Test", "Text", "$300.00"))
}