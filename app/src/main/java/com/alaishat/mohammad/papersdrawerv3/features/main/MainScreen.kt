package com.alaishat.mohammad.papersdrawerv3.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.littlelemonfinalassessment.Components.DrawerItem
import com.alaishat.mohammad.papersdrawerv2.DrawYourPaper
import com.alaishat.mohammad.papersdrawerv2.RectanglePossiblePapers
import com.alaishat.mohammad.papersdrawerv3.features.draw_papers.DrawPapersScreen
import com.alaishat.mohammad.papersdrawerv3.features.possible_papers.PossiblePapersScreen
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Al-Aishat on Apr/24/2025.
 * PaperDrawer Project.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var papers = remember {
        mutableStateListOf(
            DataPaper('A', 25, 25),
            DataPaper('B', 75, 175),
            DataPaper('C', 75, 30),
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
                        DrawerItem(
                            icon = Icons.Default.Edit,
                            label = "Rectangle Possible Papers",
                            onClick = {
                                navController.navigate(RectanglePossiblePapers.route)
                                {
                                    popUpTo(DrawYourPaper.route)
                                    launchSingleTop = true
                                }
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            })
                    }
                }
            }) {
            NavHost(navController = navController, startDestination = DrawYourPaper.route) {
                composable(DrawYourPaper.route) {
                    DrawPapersScreen()
                }
                composable(RectanglePossiblePapers.route) {
                    PossiblePapersScreen()
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
            MainScreen(modifier = modifier)
        }
    }
}
