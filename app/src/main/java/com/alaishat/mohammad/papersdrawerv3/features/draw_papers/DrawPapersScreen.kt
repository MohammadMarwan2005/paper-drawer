package com.alaishat.mohammad.papersdrawerv3.features.draw_papers

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alaishat.mohammad.papersdrawerv3.composables.FullPaperItem
import com.alaishat.mohammad.papersdrawerv3.composables.ShowMessageEventHandler

/**
 * Created by Mohammad Al-Aishat on Apr/24/2025.
 * PaperDrawer Project.
 */
@Composable
fun DrawPapersScreen(
    modifier: Modifier = Modifier,
    viewModel: DrawPapersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ShowMessageEventHandler(viewModel.viewEvent)

    Column(
        modifier = modifier
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = modifier.weight(1.5f)
        ) {
            itemsIndexed(state.papers) { index, boxTree ->
                FullPaperItem(
                    index = index,
                    boxTree = boxTree,
                    onDelete = {
                        viewModel.removePaper(index)
                    },
                    onRotate = {
                        viewModel.rotatePaper(index)
                    },
                )
            }
        }

        Row(
            modifier = modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = modifier.weight(4f),
                value = viewModel.textFieldStateValue.value,
                onValueChange = { viewModel.textFieldStateValue.value = it },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(
                modifier = modifier
                    .weight(0.2f)
                    .width(8.dp)
            )
            Column {
                Button(
                    modifier = modifier
                        .weight(1f)
                        .size(80.dp)
                        .aspectRatio(1f),
                    shape = CircleShape,
                    onClick = {
                        viewModel.addNewPaper()
                    },
                ) {
                    Text(text = "Add", style = MaterialTheme.typography.labelSmall)
                }

            }
        }

        Row {
            IconButton(onClick = {
                viewModel.loadLastValue()
            }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
            }
            IconButton(onClick = {
                viewModel.clearTextFieldValue()
            }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }
    }
}
