package com.synctech.worksync.ui.components

import android.media.SubtitleData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.withContext

@Composable
fun CreateJob(
    jobName = String,
    clientName = String,
    description = String,
    address = String,
    onClick:() ->Unit

) {
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .clickable { showAlert = true }
    ){
        Row{
            Column{
                Text(text = jobName  fontWeight = FontWeight.Bold)
                Text(text = clientName, color =  Color.Gray)
                Text(text = description, color =  Color.Gray)
                Text(text = address, color =  Color.Gray)
            }
            Spacer(modifier =  Modifier.weight(1f))
            IconButton(onClick = {onClick()}){
                Icon(imageVector=Icons.Default.Edit, contentDescription = "")
            }

        }
        Divider()
        if (showAlert){
                Alert(title = "",
                    message = "",
                    confirmText= "Aceptar",
                    onConfirmClick = {showAlert = false}){

                }
        }

    }
}