package com.example.artspace

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.junit.Rule
import org.junit.Test

class UITest {
    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun go_to_next_and_previous_art() {
        composeRule.setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
        composeRule.onNodeWithText("Next").performClick()
        composeRule.onNodeWithText("Akinola Oketoobo").assertExists()
        composeRule.onNodeWithText("Previous").performClick()
        composeRule.onNodeWithText("Obada Emeka").assertExists()
    }
}