package com.alaishat.mohammad.papersdrawerv3.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.javalibrary.backend.BoxTreeNode
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer V2 Project.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaperListItem(
    dataPaper: DataPaper = DataPaper(
        'A',
        200,
        100
    ),
    onPaperDeleted: () -> Unit
) {
    var alert by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {

            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (alert) {
            AlertDialog(onDismissRequest = { alert = !alert }) {
                PaperCard(
                    modifier = Modifier, boxTreeNode = BoxTreeNode(
                        dataPaper.value,
                        dataPaper.height,
                        dataPaper.width
                    )
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LeadingCircle(dataPaper.value)
            Text(text = dataPaper.toString())
        }
        IconButton(onClick = onPaperDeleted) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPaperListItem() {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PaperListItem { }
        }
    }
}
