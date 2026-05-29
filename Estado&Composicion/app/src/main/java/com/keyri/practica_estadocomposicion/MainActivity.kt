package com.keyri.practica_estadocomposicion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculator()
        }
    }

    @Composable
    fun TipCalculator(modifier: Modifier = Modifier) {
        var amountInput by rememberSaveable { mutableStateOf("") }
        val amount = amountInput.toDoubleOrNull() ?: 0.0
        val tip = calculateTip(amount)

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.calculate_tip),
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 40.dp)
                    .align(alignment = Alignment.Start)
            )

            TextField(
                value = amountInput,
                onValueChange = { amountInput = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.bill_amount)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.tip_amount, tip),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }

    // 👇 IMPORTANTE: mismo nombre EXACTO
    fun calculateTip(amount: Double, tipPercent: Double = 10.0): String {
        val tip = tipPercent / 100 * amount
        return NumberFormat.getCurrencyInstance().format(tip)
    }
}