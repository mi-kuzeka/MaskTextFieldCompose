package com.kuzepa.masktextfieldcompose.ui.composable

import MaskVisualTransformation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun MaskedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    mask: String,
    delimiters: CharArray,
    label: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    val maxInputLength = remember { mask.split(delimiters = delimiters).sumOf { it.length } }

    TextField(
        label = label,
        value = value,
        onValueChange = { newValue ->
            // Enforce max length
            onValueChange(newValue.take(maxInputLength))
        },
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