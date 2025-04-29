package com.alaishat.mohammad.papersdrawerv3.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.javalibrary.backend.BoxTreeNode

/**
 * Created by Mohammad Al-Aishat on Apr/24/2025.
 * PaperDrawer Project.
 */
@Composable
fun PaperCard(
    modifier: Modifier,
    boxTreeNode: BoxTreeNode,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    when (boxTreeNode.value) {
        '-' -> {
            Column {
                PaperCard(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error),
                    boxTreeNode.left,
                    color = color
                )
                PaperCard(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error),
                    boxTreeNode.right,
                    color = color
                )
            }
        }

        '|' -> {
            Row(
                modifier = Modifier,
//                verticalAlignment = Alignment.CenterVertically,
            ) {
                PaperCard(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error),
                    boxTreeNode.left,
                    color = color
                )

                PaperCard(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error),
                    boxTreeNode.right,
                    color = color
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f))
                    .requiredWidth(boxTreeNode.width.dp)
                    .requiredHeight(boxTreeNode.height.dp)
                    .border(BorderStroke(1.dp, color = color)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = boxTreeNode.value.toString())
            }
        }
    }
}