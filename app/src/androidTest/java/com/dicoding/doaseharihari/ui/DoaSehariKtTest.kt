package com.dicoding.doaseharihari.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.doaseharihari.assertCurrentRouteName
import com.dicoding.doaseharihari.data.datamodel.DummyDataDoa
import com.dicoding.doaseharihari.navigation.Screen
import com.dicoding.doaseharihari.ui.theme.DoaSehariHariTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DoaSehariKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    @Before
    fun setUp() {
        composeTestRule.setContent {
            DoaSehariHariTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                DoaSehari(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("ListDoa").performScrollToIndex(10)
        composeTestRule.onNodeWithText( DummyDataDoa.dummyDoa[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailDoa.route)
        composeTestRule.onNodeWithText(DummyDataDoa.dummyDoa[10].title).assertIsDisplayed()
    }
}