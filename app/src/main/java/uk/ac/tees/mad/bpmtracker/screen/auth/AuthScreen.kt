package uk.ac.tees.mad.bpmtracker.screen.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.bpmtracker.R
import uk.ac.tees.mad.bpmtracker.ui.theme.BPMTrackerTheme

@Composable
fun AuthScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLogin by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color(0xFFBCCCDC))){
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()
                .height(250.dp)) {
                Image(
                    painter = painterResource(R.drawable.auth_bg),
                    contentDescription = "Auth Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(modifier = Modifier.padding(top = 100.dp, start = 16.dp)) {
                    Text(if(isLogin)"Sign in to your account" else "Register new account",
                        fontSize = 40.sp,
                        lineHeight = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(if (isLogin)"Sign in to continue" else "Create new account", color = Color.White)
                }
            }
            AnimatedVisibility(!isLogin) {
                TextField(
                    value = name,
                    onValueChange = {name = it},
                    leadingIcon = { Icon(
                        Icons.Outlined.Person,
                        contentDescription = "",
                        tint = Color.Black
                    ) },
                    placeholder = { Text("Name", color = Color.Black) },
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth()
                )
            }
            TextField(
                value = email,
                onValueChange = {email = it},
                leadingIcon = { Icon(
                    Icons.Outlined.Email,
                    contentDescription = "",
                    tint = Color.Black
                ) },
                placeholder = { Text("Email", color = Color.Black) },
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = {password = it},
                leadingIcon = { Icon(
                    Icons.Outlined.Lock,
                    contentDescription = "",
                    tint = Color.Black
                ) },
                placeholder = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) ImageVector.vectorResource(R.drawable.baseline_visibility_off_24)
                    else ImageVector.vectorResource(R.drawable.baseline_visibility_24)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.weight(1f))
            if (!isLoading) {
                TextButton(onClick = {
                    if (isLogin){

                    }
                    else{

                    }
                },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF22177A)
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (isLogin) "Login" else "Sign Up",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .fillMaxWidth()) {
                    Text(if (isLogin)"Don't have an account?" else "Already have an account?", color = Color.Black)
                    TextButton(onClick = { isLogin = !isLogin }) {
                        Text(
                            text = if (isLogin) "Sign Up" else "Login",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1565C0),
                        )
                    }
                }
            }
            else{
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        color = Color.Black,
                        strokeWidth = 2.dp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    BPMTrackerTheme {
        AuthScreen()

    }
}