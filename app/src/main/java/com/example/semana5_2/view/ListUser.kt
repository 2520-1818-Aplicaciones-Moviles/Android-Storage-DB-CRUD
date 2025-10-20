package com.example.semana5_2.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun ListUser(saveScreen: NavHostController, context: Context, viewModel: UserViewModel) {

    // Load users when the composable is first displayed
    LaunchedEffect(Unit) {
        viewModel.showUsers(context)
    }

    // Status for the edit dialog and delete confirmation user
    var editingUser by remember { mutableStateOf<User?>(null) }
    var showDeleteConfirmFor by remember { mutableStateOf<User?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { saveScreen.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "USERS LIST",
                fontSize =25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            // Placeholder to keep title centered
            IconButton(onClick = { viewModel.showUsers(context) }) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.Transparent)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(viewModel.listUser_, key = { it.id }){ user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .heightIn(min=100.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .width(170.dp)
                        ) {
                            Text(text = "Code: ${user.id}")
                            Text(text = "Name: ${user.name}")
                            Text(text = "Age: ${user.age}")
                            Text(text = "Email: ${user.email}")
                        }

                        IconButton(
                            onClick = { showDeleteConfirmFor = user }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null
                            )
                        }

                        IconButton(
                            onClick = { editingUser = user.copy() }
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null
                            )
                        }

                    }
                }
            }
        }
    }

    /**
     * Dialog for editing a user
     * */
    editingUser?.let { u ->
        var name by remember(u.id) { mutableStateOf(u.name) }
        var ageText by remember(u.id) { mutableStateOf(u.age.toString()) }
        var email by remember(u.id) { mutableStateOf(u.email) }
        var nameErr by remember { mutableStateOf(false) }
        var ageErr by remember { mutableStateOf(false) }
        var emailErr by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { editingUser = null },
            title = { Text("Edit User #${u.id}") },
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it; nameErr = false },
                        label = { Text("Name") },
                        isError = nameErr,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = ageText,
                        onValueChange = { ageText = it.filter { c -> c.isDigit() }; ageErr = false },
                        label = { Text("Age") },
                        isError = ageErr,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it; emailErr = false },
                        label = { Text("Email") },
                        isError = emailErr,
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val ageInt = ageText.toIntOrNull()
                    nameErr = name.isBlank()
                    ageErr = ageInt == null
                    emailErr = email.isBlank()
                    if (!nameErr && !ageErr && !emailErr) {
                        viewModel.updateUser(context, User(u.id, name.trim(), ageInt!!, email.trim()))
                        Toast.makeText(context, "Updated user", Toast.LENGTH_SHORT).show()
                        editingUser = null
                    }
                }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { editingUser = null }) { Text("Cancel") }
            }
        )
    }

    /**
     * Confirm the deletion with a dialog
    */
    showDeleteConfirmFor?.let { u ->
        AlertDialog(
            onDismissRequest = { showDeleteConfirmFor = null },
            title = { Text("Delete User") },
            text = { Text("Are you sure you want to delete ${u.name}?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteUser(context, u.id)
                    Toast.makeText(context, "Deleted user", Toast.LENGTH_SHORT).show()
                    showDeleteConfirmFor = null
                }) { Text("Delete", color = Color.Red) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmFor = null }) { Text("Cancel") }
            }
        )
    }
}