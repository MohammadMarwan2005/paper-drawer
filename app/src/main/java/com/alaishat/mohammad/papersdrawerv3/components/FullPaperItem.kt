package com.alaishat.mohammad.papersdrawerv2

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer V2 Project.
 */


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.papersdrawerv3.YourPaper
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.BoxTreeNode
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme

@Composable
fun FullPaperItem(
    modifier: Modifier = Modifier,
    it: Int = 0,
    boxTree: BoxTree = BoxTree(
        BoxTreeNode('A', 50, 50)
    ),
    onDelete: () -> Unit = {},
    onRotate: () -> Unit = {},
) {
    Box(modifier = modifier.padding(32.dp), contentAlignment = Alignment.TopCenter) {
        Row(
            modifier = modifier
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = (-50).dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberComp(it = it)
            if (boxTree.isRectangle)
                Text(
                    text = "This is a full-rectangle shape!",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                )
            else
                Text(
                    text = "This is not a full-rectangle shape!",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                )
            IconButton(
                onClick = onDelete
//                {
////                    array.removeAt(it)
//                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete!")
            }
            IconButton(
                onClick = onRotate
//                {
//                    val newRoot = array[it].rotate()
//                    array[it] = BoxTree(newRoot)
////                                array.add(BoxTree(newRoot))
////                                array.remove(array[it])
//                }
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Rotate!")
            }
        }
        Column(
            modifier = modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color =
//                                        if (it.isRectangle)
                            MaterialTheme.colorScheme.onBackground
//                                        else MaterialTheme.colorScheme.error
                        )
                    )
            ) {
                YourPaper(
                    modifier = modifier, boxTreeNode = boxTree.root,
                    color =
//                                    if (it.isRectangle)
                    MaterialTheme.colorScheme.onBackground
//                                    else MaterialTheme.colorScheme.error
                )
            }
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
            .padding(vertical = 4.dp, horizontal = 8.dp)
        ,
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
