package com.kuzepa.masktextfieldcompose.ui.composable

import MaskVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OnlyDigitsMaskedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    mask: String,
    delimiters: CharArray,
    label: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    val maxDigits = remember { mask.split(delimiters = delimiters).sumOf { it.length } }

    TextField(
        label = label,
        value = value,
        onValueChange = { newValue ->
            // Filter non-digit characters and enforce max length
            val filteredText = newValue.filter { it.isDigit() }.take(maxDigits)
            onValueChange(filteredText)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskVisualTransformation(
            mask = mask,
            delimiters = delimiters,
            textColor = MaterialTheme.colorScheme.onSurface,
            maskColor = MaterialTheme.colorScheme.outline
        ),
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconClear(onValueChange = onValueChange)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer
        ),
        modifier = modifier,
    )
}