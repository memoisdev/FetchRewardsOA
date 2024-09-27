package com.onlineassignment.fetchrewards.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.onlineassignment.fetchrewards.challenge.ui.screen.GroupedHiringItemsScreen
import com.onlineassignment.fetchrewards.challenge.ui.theme.FetchRewardsOATheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsOATheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GroupedHiringItemsScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}
