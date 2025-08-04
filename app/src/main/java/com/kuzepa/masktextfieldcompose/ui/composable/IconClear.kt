package com.kuzepa.masktextfieldcompose.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun IconClear(onValueChange: (String) -> Unit) {
    IconButton(onClick = { onValueChange("") }) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}