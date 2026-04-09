package com.keyri.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keyri.lemonadeapp.ui.theme.LemonadeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("Lemonade")
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color(0xFFFFEB3B)
                            )
                        )
                    }
                ) { innerPadding ->
                    LemonadeApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var squeezeTarget by remember { mutableStateOf((2..4).random()) }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (step) {
        1 -> R.string.tap_tree
        2 -> R.string.keep_tapping
        3 -> R.string.tap_drink
        else -> R.string.tap_restart
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = textResource),
            modifier = Modifier.clickable {
                when (step) {
                    1 -> step = 2
                    2 -> {
                        squeezeCount++
                        if (squeezeCount >= squeezeTarget) {
                            step = 3
                            squeezeCount = 0
                            squeezeTarget = (2..4).random()
                        }
                    }

                    3 -> step = 4
                    4 -> step = 1
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = textResource)
        )
    }
}