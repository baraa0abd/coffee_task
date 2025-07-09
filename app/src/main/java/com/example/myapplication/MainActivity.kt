package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.component.CoffeePercentage
import com.example.myapplication.screens.CoffeeChoiceScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.PrepareCoffeeScreen
import com.example.myapplication.screens.WelcomeScreen
import com.example.myapplication.component.CupSizeSwitcherScreen
import com.example.myapplication.screens.DrinkImageSwitcher
import com.example.myapplication.models.DrinkType
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                CoffeeApp()
            }
        }
    }
}

@Composable
fun CoffeeApp() {
    // Screen state management
    var currentScreen by rememberSaveable { mutableStateOf("home") }

    // Coffee order state
    var selectedCupSize by rememberSaveable { mutableStateOf("M") }
    var selectedIntensity by rememberSaveable { mutableStateOf("Medium") }
    var selectedDrinkIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedDrinkType by rememberSaveable { mutableStateOf("Macchiato") }

    // Available drinks data
    val availableDrinks = remember {
        listOf(
            DrinkType(name = "Espresso", drawableRes = R.drawable.espresso),
            DrinkType(name = "Latte", drawableRes = R.drawable.latte),
            DrinkType(name = "Macchiato", drawableRes = R.drawable.mocchiato),
            DrinkType(name = "Americano", drawableRes = R.drawable.americano),
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (currentScreen) {
            // Screen 1: Home - Landing screen with focus message
            "home" -> HomeScreen(
                modifier = Modifier.padding(innerPadding),
                onProfileClick = { /* Handle profile navigation if needed */ },
                onAddClick = { currentScreen = "welcome" },
                onBringCoffeeClick = { currentScreen = "welcome" }
            )

            // Screen 2: Welcome - Drink selection screen
            "welcome" -> WelcomeScreen(
                onProfileClick = { currentScreen = "home" },
                onAddClick = { currentScreen = "drinkSelection" },
                onContinueClick = { currentScreen = "drinkSelection" }
            )

            // Screen 2a: Drink Selection (if using separate screen)
            "drinkSelection" -> DrinkImageSwitcher(
                drinks = availableDrinks,
                modifier = Modifier.padding(innerPadding),
                onDrinkSelected = { index ->
                    selectedDrinkIndex = index
                    selectedDrinkType = availableDrinks[index].name
                    currentScreen = "coffeeChoice"
                }
            )

            // Screen 3: Coffee Choice - Size and intensity selection
            "coffeeChoice" -> CoffeeChoiceScreen(
                onBack = { currentScreen = "welcome" },
                onContinue = { size: String ->
                    selectedCupSize = size
                    currentScreen = "prepareCoffee"
                }
            )

            "prepareCoffee" -> PrepareCoffeeScreen(
                cupSize = selectedCupSize,
                drinkType = selectedDrinkType,
                intensity = selectedIntensity,
                onBack = { currentScreen = "coffeeChoice" },
                onComplete = {
                    selectedCupSize = "M"
                    selectedIntensity = "Medium"
                    selectedDrinkIndex = 0
                    currentScreen = "home"
                }
            )

            "cupSizeSwitcher" -> CupSizeSwitcherScreen(
                onBack = { currentScreen = "coffeeChoice" }
            )

            "coffeePercentage" -> CoffeePercentage()

            else -> {
                currentScreen = "home"
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onProfileClick = { },
                    onAddClick = { currentScreen = "welcome" },
                    onBringCoffeeClick = { currentScreen = "welcome" }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CoffeeAppLightPreview() {
    MyApplicationTheme {
        CoffeeApp()
    }
}