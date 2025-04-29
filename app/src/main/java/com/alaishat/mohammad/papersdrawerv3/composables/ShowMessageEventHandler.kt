package com.alaishat.mohammad.papersdrawerv3.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.alaishat.mohammad.papersdrawerv3.features.draw_papers.ShowMessageEvent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Al-Aishat on Apr/29/2025.
 * PaperDrawer Project.
 */
@Composable
fun ShowMessageEventHandler(eventFlow: Flow<ShowMessageEvent>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
        }
    }
}
