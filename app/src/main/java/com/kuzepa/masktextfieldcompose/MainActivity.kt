package com.kuzepa.masktextfieldcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuzepa.masktextfieldcompose.ui.composable.MaskedTextField
import com.kuzepa.masktextfieldcompose.ui.composable.OnlyDigitsMaskedTextField
import com.kuzepa.masktextfieldcompose.ui.theme.MaskTextFieldComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaskTextFieldComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        DateTextField()
        PhoneNumberTextField()
        OtherMaskTextField()
    }
}

@Composable
fun DateTextField() {
    var date by remember {
        mutableStateOf("")
    }
    OnlyDigitsMaskedTextField(
        value = date,
        onValueChange = { date = it },
        mask = "mm/dd/yyyy",
        delimiters = charArrayOf('/'),
        label = { Text("Date") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun PhoneNumberTextField() {
    var number by remember {
        mutableStateOf("")
    }
    OnlyDigitsMaskedTextField(
        value = number,
        onValueChange = { number = it },
        mask = "+_ (___) ___-__-__",
        delimiters = charArrayOf('+', ' ', '(', ')', '-'),
        label = { Text("Phone number") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun OtherMaskTextField() {
    var value by remember {
        mutableStateOf("")
    }
    MaskedTextField(
        value = value,
        onValueChange = { value = it },
        mask = "        -    -    -    -            ",
        delimiters = charArrayOf('-'),
        label = { Text("UUID") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaskTextFieldComposeTheme {
        Content()
    }
}