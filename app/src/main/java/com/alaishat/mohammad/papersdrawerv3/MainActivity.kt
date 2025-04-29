package com.alaishat.mohammad.papersdrawerv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alaishat.mohammad.papersdrawerv3.features.main.MainScreen
import com.alaishat.mohammad.papersdrawerv3.ui.theme.PapersDrawerV3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapersDrawerV3Theme {
                MainScreen()
            }
        }
    }
}
