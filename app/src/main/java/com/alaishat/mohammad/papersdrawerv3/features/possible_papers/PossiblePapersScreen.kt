package com.alaishat.mohammad.papersdrawerv3.features.possible_papers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.papersdrawerv3.composables.FullPaperItem
import com.alaishat.mohammad.papersdrawerv3.composables.PaperListItem
import com.alaishat.mohammad.papersdrawerv3.composables.ShowMessageEventHandler

/**
 * Created by Mohammad Al-Aishat on Apr/29/2025.
 * PaperDrawer Project.
 */
@Composable
fun PossiblePapersScreen(
    viewModel: PossiblePapersViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ShowMessageEventHandler(viewModel.viewEvent)

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            PossiblePapersContent(
                papers = state.papers,
                boxTrees = state.boxTrees,
                onPaperAdded = viewModel::addDataPaper,
                onPaperRemoved = viewModel::removeDataPaper,
                isLoading = state.isLoading
            )
        }
    }
}

@Composable
private fun ColumnScope.PossiblePapersContent(
    papers: List<DataPaper>,
    boxTrees: List<BoxTree>,
    onPaperAdded: (charString: String, heightString: String, widthString: String) -> Unit,
    onPaperRemoved: (index: Int) -> Unit,
    isLoading: Boolean
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
        text = if (isLoading) "Loading..." else "${boxTrees.size} Possible Cases"
    )
    LazyRow(
        modifier = Modifier.weight(2f)
    ) {
        itemsIndexed(boxTrees) { index, boxTree ->
            FullPaperItem(
                index = index,
                boxTree = boxTree,
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
    ) {
        items(papers.size) {
            PaperListItem(
                dataPaper = papers[it],
                onPaperDeleted = { onPaperRemoved(it) },
            )
        }
    }

    Adder(
        modifier = Modifier.weight(0.5f),
        onPaperAdded = onPaperAdded,
        buttonEnabled = papers.size <= 4,
        isLoading = isLoading
    )
}

@Composable
fun Adder(
    modifier: Modifier = Modifier,
    onPaperAdded: (charString: String, heightString: String, widthString: String) -> Unit,
    buttonEnabled: Boolean = true,
    isLoading: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var charTextFiledValue by rememberSaveable {
            mutableStateOf("D")
        }
        var heightTextFiledValue by rememberSaveable {
            mutableStateOf("25")
        }
        var widthTextFiledValue by rememberSaveable {
            mutableStateOf("25")
        }
        OutlinedTextField(
            modifier = modifier.weight(3f),
            value = charTextFiledValue, onValueChange = { charTextFiledValue = it },
            label = { Text(text = "Char", style = MaterialTheme.typography.bodySmall) }
        )
        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = heightTextFiledValue,
            onValueChange = { heightTextFiledValue = it },
            label = { Text(text = "Height", style = MaterialTheme.typography.bodySmall) })
        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = widthTextFiledValue,
            onValueChange = { widthTextFiledValue = it },
            label = { Text(text = "Width", style = MaterialTheme.typography.bodySmall) })
        Button(
            modifier = modifier
                .weight(1f)
                .aspectRatio(1f),
            shape = CircleShape,
            onClick = {
                onPaperAdded(
                    charTextFiledValue,
                    heightTextFiledValue,
                    widthTextFiledValue
                )
            },
            enabled = buttonEnabled
        ) {
            if (isLoading) CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            else Text(text = "Add")
        }
    }
}
