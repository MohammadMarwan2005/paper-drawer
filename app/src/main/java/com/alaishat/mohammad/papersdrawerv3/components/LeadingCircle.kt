package com.alaishat.mohammad.papersdrawerv3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer V3 Project.
 */


@Composable
fun LeadingCircle(char: Char = 'M') {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$char", color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLeadingCircle() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LeadingCircle()
        }
    }
}
