package com.alaishat.mohammad.papersdrawerv3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.BoxTreeNode
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.littlelemonfinalassessment.Components.DrawerItem
import com.alaishat.mohammad.papersdrawerv2.DrawYourPaper
import com.alaishat.mohammad.papersdrawerv2.FullPaperItem
import com.alaishat.mohammad.papersdrawerv2.RectanglePossiblePapers
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapersDrawerV3Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val s = "((A[100,100] | (B[100,100]|C[100,100])) - (D[150,150] | (E[75,150] - F[75,100])))"
    val listState = remember {
        mutableStateListOf(
            BoxTree(s.toCharArray().toList())
        )
    }
    val context = LocalContext.current
    var textFieldStateValue by rememberSaveable {
        mutableStateOf(s)
    }

    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var papers = remember {
        mutableStateListOf(
            DataPaper('A', 25, 25),
            DataPaper('B', 75, 175),
            DataPaper('C', 75, 30),
//            DataPaper('D', 15, 35),
//            DataPaper('E', 50, 25),
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Box(modifier = modifier.padding(12.dp)) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        if (drawerState.currentValue == DrawerValue.Closed) {
                            drawerState.open()
                        } else
                            drawerState.close()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                }
            }
//            TopAppBar(
//                modifier = modifier.fillMaxWidth(),
//                title = {
//                    Text(
//                        modifier = modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center,
//                        text = "Draw Your Paper!"
//                    )
//                },
//            )
        },
    ) { scaffoldPaddingValues ->
        ModalNavigationDrawer(
            modifier = modifier.padding(scaffoldPaddingValues),
            drawerState = drawerState,
            drawerContent = {
                LazyColumn(
                    modifier = modifier
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 12.dp)
                ) {
                    item {
                        DrawerItem(icon = Icons.Default.Edit, label = "Draw Your Paper", onClick = {
                            navController.navigate(DrawYourPaper.route)
                            {
                                launchSingleTop = true
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        })
                    }
                    item {
                        DrawerItem(icon = Icons.Default.Edit, label = "Rectangle Possible Papers", onClick = {
//                            navController.navigate(RectanglePossiblePapers.route)
                            navController.navigate(RectanglePossiblePapers.route)
                            {
                                popUpTo(DrawYourPaper.route)
//                                {
//                                    inclusive = true
//                                }
                                launchSingleTop = true
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        })
                    }
//                    item {
//                        DrawerItem(icon = Icons.Default.Edit, label = "My Papers", onClick = {
//                            navController.navigate(RectanglePossiblePapers.route) {
//                                popUpTo(DrawYourPaper.route) {
//                                    inclusive = true
//                                }
//                                launchSingleTop = true
//                            }
//                            coroutineScope.launch {
//                                drawerState.close()
//                            }
//                        })
//                    }
                }
            }) {
//            Spacer(modifier = Modifier.height(8.dp))

            NavHost(navController = navController, startDestination = DrawYourPaper.route) {
                composable(DrawYourPaper.route) {
                    Column(
                        modifier = modifier
                            .padding(8.dp)
                            .padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyRow(
                            modifier = modifier.weight(1f)
                        ) {
                            items(listState.size) {
                                FullPaperItem(
                                    modifier = modifier,
                                    it,
                                    boxTree = listState[it],
                                    onDelete = { listState.removeAt(it) },
                                    onRotate = {
                                        val newRoot = listState[it].rotate()
                                        listState[it] =
                                            BoxTree(newRoot)

                                    }
                                )
                            }
                        }

                        var temp by rememberSaveable {
                            mutableStateOf("")
                        }

                        Row(
                            modifier = modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                modifier = modifier.weight(4f),
                                value = textFieldStateValue, onValueChange = { textFieldStateValue = it },
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
                                        try {
                                            val boxTree =
                                                BoxTree(
                                                    textFieldStateValue.toCharArray().toList()
                                                )
                                            listState.add(boxTree)
                                            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT)
                                                .show()
                                            Toast.makeText(
                                                context,
                                                "You added a ${if (boxTree.isRectangle) "" else "non-"}rectangle shape",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            if (textFieldStateValue != "")
                                                temp = textFieldStateValue
                                            textFieldStateValue = ""
                                        } catch (e: Exception) {
                                            Toast.makeText(
                                                context,
                                                "Refactor your input, please...",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    },
                                ) {
                                    Text(text = "Add")
                                }

                            }
                        }

                        Row {

                            IconButton(onClick = {
                                if (temp != "") textFieldStateValue = temp else Toast.makeText(
                                    context,
                                    "No old data!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                            }
                            IconButton(onClick = {
                                if (textFieldStateValue != "") {
                                    temp = textFieldStateValue
                                    textFieldStateValue = ""
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                            }
                        }
                    }

                }
                composable(RectanglePossiblePapers.route) {
                    RectanglePossiblePapers(
                        dataPapers = papers,
                        onPaperAdded = {
                            papers.add(it)
                        },
                        onPaperRemoved = {
                            papers.removeAt(it)
                        }
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewApp(modifier: Modifier = Modifier) {
    PapersDrawerV3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App(modifier = modifier)
        }
    }
}


@Composable
fun YourPaper(
    modifier: Modifier,
    boxTreeNode: BoxTreeNode,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    when (boxTreeNode.value) {
        '-' -> {
            Column {
                YourPaper(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error), boxTreeNode.left, color = color
                )
                YourPaper(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error), boxTreeNode.right, color = color
                )
            }
        }

        '|' -> {
            Row(
                modifier = Modifier,
//                verticalAlignment = Alignment.CenterVertically,
            ) {
                YourPaper(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error), boxTreeNode.left, color = color
                )

                YourPaper(
                    modifier
                        .background(color = MaterialTheme.colorScheme.error), boxTreeNode.right, color = color
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


    @Composable
    fun YourCard(modifier: Modifier, boxTreeNode: BoxTreeNode) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = boxTreeNode.exportNode(), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = modifier.height(16.dp))
            YourPaper(modifier = modifier, boxTreeNode = boxTreeNode)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
//    @Preview(showBackground = true)
    @Composable
    fun FirstScreen(
        modifier: Modifier = Modifier,
        treeNodeList: List<BoxTreeNode> = listOf(
            BoxTree(
                "((A[100,100] | (B[100,100]|C[100,100])) - (D[150,150] | (E[75,150] - F[75,150])))".toCharArray()
                    .toList()
            ).root,
            BoxTree(
                "((A[100,100] | (B[100,100]|C[100,100])) - (D[150,150] | (E[75,150] - F[75,150])))".toCharArray()
                    .toList()
            ).root
        ),
        onAddElement: () -> Unit = {},
        textFieldStateValue: String = "",
        textFieldOnValueChange: (String) -> Unit = {},
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = modifier.fillMaxWidth(),
                    title = {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Draw Your Paper!"
                        )
                    },
                )
            },
        ) {
            Column(
                modifier = modifier
                    .padding(it)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyRow(
                    modifier = modifier
                ) {
                    items(treeNodeList.size) {
                        Box(
                            modifier = modifier
                                .verticalScroll(rememberScrollState())
                                .padding(32.dp),
                            contentAlignment = Alignment.TopCenter,
                        ) {
                            Column(
                                modifier = modifier,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                YourPaper(modifier = modifier, boxTreeNode = treeNodeList[it])
                                Text(
                                    modifier = modifier.width(300.dp),
                                    text = treeNodeList[it].exportNode(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }


                Row(
                    modifier = modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(value = textFieldStateValue, onValueChange = textFieldOnValueChange)
                    Spacer(modifier = modifier.width(8.dp))
                    Button(
                        modifier = modifier.size(80.dp),
                        shape = CircleShape,
                        onClick = onAddElement
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}
