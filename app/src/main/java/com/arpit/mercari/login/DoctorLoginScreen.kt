package com.arpit.mercari.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arpit.mercari.R
import com.arpit.mercari.compose.components.ProgressDialog
import com.arpit.mercari.util.getActivity

@Composable
fun DoctorLoginScreen(
    viewModel: DoctorSignUpViewModel,
    onSignUpClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.doctorSignInEvents.collect { event ->
            when (event) {
                NavigateToHome -> navigateToHome()
            }
        }
    }
    viewModel.loginScreen = true
    val activity = LocalContext.current.getActivity()
    viewModel.progressBarText?.let { ProgressDialog(text = it) }
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (viewModel.otpScreen) {
            OTPVerification(
                phoneNumber = viewModel.loginNumber,
                verifyOtp = viewModel::signInUsingOTP,
                modifier = Modifier.align(Alignment.Center)
            )
        } else
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.doctor_icon),
                        contentDescription = null,
                        modifier = Modifier.size(84.dp)
                    )
                    var number by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = number,
                        onValueChange = { number = it },
                        label = {
                            Text(
                                text = "Enter phone number",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = { viewModel.sendOtp(activity!!) },
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Text(
                            text = "Send OTP",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "Don't have an account?",
                        color = MaterialTheme.colors.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sign up",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable(onClick = onSignUpClick)
                            .padding(4.dp)
                    )
                }
            }
    }
}