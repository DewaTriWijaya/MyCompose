package com.example.navigationjetpackcompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationjetpackcompose.R
import com.example.navigationjetpackcompose.ui.components.FormattedPriceLabel

// Composable that displays the list of items as [RadioButton] options,
// [onSelectedChanged] lambda that notifies the parent composable when a new value is selected,
// [onCancelButtonClicked] lambda that cancels the order when user clicks cancel and
// [onNextButtonClicked] lambda that triggers the navigation to next screen

@Composable
fun SelectOptionsScreen(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    // TODO: add onCancelButtonClicked
    // TODO: add onNextButtonClicked
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(text = item)
            }
        }
        Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
        FormattedPriceLabel(
            subTotal = subtotal,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionScreen() {
    SelectOptionsScreen(
        subtotal = "299.99",
        options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    )
}