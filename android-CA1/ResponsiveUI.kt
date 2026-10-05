package com.cse226.application.CA1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ResponsiveUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResponsiveNewsCard(title = "Compose is fun")
        }
    }
}

@Composable
fun ResponsiveNewsCard(title: String) {
    BoxWithConstraints(Modifier.fillMaxWidth().padding(8.dp)) {
        val w = maxWidth

        if (w < 360.dp) {
            Column {
                Icon(Icons.Default.Image, contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(120.dp))
                Text(title)
                Row {
                    Button(onClick = { Log.d("ResponsiveUI", "Vertical UI runned") }) { Text("Like") }
                }
            }
        } else if (w < 600.dp) {
            Column {
                Icon(Icons.Default.Image, contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(140.dp))
                Row {
                    Text(title, modifier = Modifier.weight(1f))
                    Button(onClick = { Log.d("ResponsiveUI", "Horizontal UI runned") }) { Text("Like") }
                }
            }
        } else {
            Row {
                Icon(Icons.Default.Image, contentDescription = null,
                    modifier = Modifier.weight(0.4f).height(160.dp))
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(title)
                    Row {
                        Button(onClick = { Log.d("ResponsiveUI", "Side-by-Side UI runned") }) { Text("Like") }
                    }
                }
            }
        }
    }
}