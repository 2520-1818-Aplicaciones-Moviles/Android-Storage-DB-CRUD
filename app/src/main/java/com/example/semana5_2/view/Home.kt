package com.example.semana5_2.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.semana5_2.model.User
import com.example.semana5_2.viewmodels.UserViewModel

@Composable
fun Home(saveScreen: NavHostController, context: Context, viewModel: UserViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textName by remember { mutableStateOf("") }
        var textEmail by remember { mutableStateOf("") }
        var textAge by remember { mutableStateOf("") }

        Text(
            text = "CRUD - ROOM",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Blue
        )

        OutlinedTextField(
            value = textName,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            label = {Text(text = "Name")},
            placeholder = {Text(text = "Insert your name")},
            onValueChange = {textName = it}
        )

        OutlinedTextField(
            value = textAge,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            label = {Text(text = "Age")},
            placeholder = {Text(text = "Insert your Age")},
            onValueChange = {textAge = it}
        )

        OutlinedTextField(
            value = textEmail,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            label = {Text(text = "Email")},
            placeholder = {Text(text = "Insert your Email")},
            onValueChange = {textEmail = it}
        )

        ElevatedButton(
            onClick = {
                viewModel.addUser(
                    context, User(
                        0,
                        textName.toString(),
                        textAge.toInt(),
                        textEmail.toString()
                    )
                )
                textName = ""
                textAge = ""
                textEmail = ""
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Save User",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}