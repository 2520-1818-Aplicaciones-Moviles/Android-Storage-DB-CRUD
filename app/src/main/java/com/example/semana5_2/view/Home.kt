package com.example.semana5_2.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
        var nameError by remember { mutableStateOf(false) }
        var ageError by remember { mutableStateOf(false) }
        var emailError by remember { mutableStateOf(false) }

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
            shape = RoundedCornerShape(24.dp),
            label = {Text(text = "Name")},
            placeholder = {Text(text = "Insert your name")},
            isError = nameError,
            onValueChange = { textName = it; nameError = false }
        )

        OutlinedTextField(
            value = textAge,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            label = {Text(text = "Age")},
            placeholder = {Text(text = "Insert your Age")},
            isError = ageError,
            onValueChange = { textAge = it.filter { c -> c.isDigit() }; ageError = false }
        )

        OutlinedTextField(
            value = textEmail,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            label = {Text(text = "Email")},
            placeholder = {Text(text = "Insert your Email")},
            isError = emailError,
            onValueChange = { textEmail = it; emailError = false }
        )

        ElevatedButton(
            onClick = {
                val ageInt = textAge.toIntOrNull()
                nameError = textName.isBlank()
                ageError = ageInt == null
                emailError = textEmail.isBlank()
                if (!nameError && !ageError && !emailError) {
                    viewModel.addUser(
                        context, User(
                            0,
                            textName.trim(),
                            ageInt!!,
                            textEmail.trim()
                        )
                    )
                    Toast.makeText(context, "user save correctly", Toast.LENGTH_SHORT).show()
                    textName = ""; textAge = ""; textEmail = ""
                } else {
                    Toast.makeText(context, "Complete the fields correctly", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Save User",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        ElevatedButton(
            onClick = {
                saveScreen.navigate("V2")
            }
        ) {
            Text(
                text = "List Users",
                fontSize =  20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}