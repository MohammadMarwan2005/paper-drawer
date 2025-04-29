package com.alaishat.mohammad.papersdrawerv3.composables



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.BoxTreeNode
import com.alaishat.mohammad.papersdrawerv3.R
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer V2 Project.
 */
@Composable
fun FullPaperItem(
    modifier: Modifier = Modifier,
    index: Int = 0,
    boxTree: BoxTree = BoxTree(
        BoxTreeNode('A', 50, 50)
    ),
    onDelete: (() -> Unit)? = null,
    onRotate: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberComp(it = index)
            if (boxTree.isRectangle)
                Text(
                    text = stringResource(R.string.this_is_a_full_rectangle_shape),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                )
            else
                Text(
                    text = "This is not a full-rectangle shape!",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            onDelete?.let {
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete!")
                }
            }
            onRotate?.let {
                IconButton(
                    onClick = onRotate
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Rotate!")
                }
            }
        }
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                .border(
                    BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
        ) {
            PaperCard(
                modifier = modifier, boxTreeNode = boxTree.root,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
        SelectionContainer {
            Text(
                modifier = modifier.width(300.dp),
                text = boxTree.root.exportNode(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFullPaperItem() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FullPaperItem(

            )
        }
    }
}

@Composable
fun NumberComp(it: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${it + 1}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNumberComp() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NumberComp(
                2
            )
        }
    }
}
