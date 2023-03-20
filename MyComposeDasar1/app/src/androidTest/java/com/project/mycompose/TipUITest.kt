package com.project.mycompose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.project.mycompose.modul.basic2.TipTimeScreen
import com.project.mycompose.ui.theme.MyComposeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composableTestRule.setContent {
            MyComposeTheme {
                TipTimeScreen()
            }
        }
        composableTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composableTestRule.onNodeWithText("Tip (%)")
            .performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composableTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}