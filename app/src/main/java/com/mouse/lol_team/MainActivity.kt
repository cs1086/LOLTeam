package com.mouse.lol_team

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mouse.lol_team.ui.theme.LOLTeamTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random
class MainActivity : ComponentActivity() {
    val mainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        mainViewModel.getSummonner()
        super.onCreate(savedInstanceState)
        setContent {
            LOLTeamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(mainViewModel: MainViewModel) {
    val AList by mainViewModel.AList.collectAsState()
    val BList by mainViewModel.BList.collectAsState()
    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "全部名單 : ${mainViewModel.playerList.joinToString("、")}", fontSize = 20.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { mainViewModel.randGroup() }) {
                Text("隨機分組", fontSize = 20.sp)
            }
            Button(onClick = { mainViewModel.clearGroup() }) {
                Text("清空", fontSize = 20.sp)
            }
        }
        Row(modifier = Modifier.fillMaxSize()) {
            if (AList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .animateContentSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(AList) { i, v ->
                        Text(text = v, fontSize = 20.sp)
                    }
                }
            }
            if (BList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .animateContentSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(BList) { i, v ->
                        Text(text = v, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}