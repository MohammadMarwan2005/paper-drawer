package com.alaishat.mohammad.papersdrawerv2

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer Project.
 */


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.javalibrary.backend.AllPossibleCasesSolution
import com.alaishat.mohammad.papersdrawerv3.composables.PaperListItem
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme

// todo: refactor this
@SuppressLint("MutableCollectionMutableState")
@Composable
fun RectanglePossiblePapers(
    modifier: Modifier = Modifier,
    dataPapers: List<DataPaper> = listOf(
        DataPaper('A', 25, 25),
        DataPaper('B', 75, 175),
        DataPaper('C', 75, 30),
        DataPaper('D', 15, 35),
        DataPaper('E', 50, 25),
//        DataPaper('E', 100, 50),
    ),
    onPaperAdded: (DataPaper) -> Unit = {},
    onPaperRemoved: (Int) -> Unit = {},
) {
    var array: SnapshotStateList<BoxTree> = remember {
        mutableStateListOf<BoxTree>()
    }


    val coroutineScope = rememberCoroutineScope()

    array =
        AllPossibleCasesSolution.getAllPossibleBoxTrees(dataPapers).sortedBy { it.root.exportNode().length }.filter { it.isRectangle }
            .toMutableStateList()



    Column(
        modifier = modifier
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = modifier.weight(1f)
        ) {
            items(array.size) {
                FullPaperItem(
                    modifier = modifier,
                    index = it,
                    boxTree = array[it],
                    onDelete = { array.removeAt(it) },
                    onRotate = {
                        val newRoot = array[it].rotate()
                        array[it] = BoxTree(newRoot)
//                        array.add(BoxTree(newRoot))
//                        array.remove(array[it])
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
        ) {
            items(dataPapers.size) {
                PaperListItem(dataPapers[it], onPaperDeleted = { onPaperRemoved(it) })
            }
        }

        Adder(
            modifier = modifier.weight(0.2f),
            onPaperAdded = onPaperAdded,
            buttonEnabled = dataPapers.size <= 4
        )
    }


}

@Preview(showBackground = true)
@Composable
private fun PreviewRectanglePapers() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RectanglePossiblePapers()
        }
    }
}

@Composable
fun Adder(modifier: Modifier = Modifier, onPaperAdded: (DataPaper) -> Unit, buttonEnabled: Boolean = true) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var charTextFiledValue by rememberSaveable {
            mutableStateOf("")
        }
        var heightTextFiledValue by rememberSaveable {
            mutableStateOf("")
        }
        var widthTextFiledValue by rememberSaveable {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = modifier.weight(3f),
            value = charTextFiledValue, onValueChange = { charTextFiledValue = it },
            label = { Text(text = "Char", style = MaterialTheme.typography.bodyMedium) }
        )
        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = heightTextFiledValue,
            onValueChange = { heightTextFiledValue = it },
            label = { Text(text = "Height", style = MaterialTheme.typography.bodyMedium) })
        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = widthTextFiledValue,
            onValueChange = { widthTextFiledValue = it },
            label = { Text(text = "Width", style = MaterialTheme.typography.bodyMedium) })
        Button(
            modifier = modifier
                .weight(1f)
                .aspectRatio(1f),
            shape = CircleShape,
            onClick = {
                onPaperAdded(
                    DataPaper(
                        charTextFiledValue.get(0),
                        heightTextFiledValue.toInt(),
                        widthTextFiledValue.toInt(),
                    )
                )
            },
            enabled = buttonEnabled
        ) {
            Text(text = "Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdder() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            Adder()
        }
    }
}
