package com.arpit.mercari.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
fun PatientSignUpScreen(
    viewModel: PatientSignUpViewModel,
    onLoginClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.patientSignInEvents.collect { event ->
            when (event) {
                NavigateToHomeScreen -> navigateToHome()
            }
        }
    }
    val context = LocalContext.current.getActivity()
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
                phoneNumber = viewModel.details.phoneNumber,
                verifyOtp = viewModel::signInUsingOTP,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(24.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.patient_icon),
                        contentDescription = null,
                        modifier = Modifier.size(84.dp)
                    )
                    OutlinedTextField(
                        value = viewModel.details.name,
                        onValueChange = { viewModel.details = viewModel.details.copy(name = it) },
                        label = {
                            Text(
                                text = "Enter patient name",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = viewModel.details.nhId,
                        onValueChange = { viewModel.details = viewModel.details.copy(nhId = it) },
                        label = {
                            Text(
                                text = "Enter NHID",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = viewModel.details.address,
                        onValueChange = {
                            viewModel.details = viewModel.details.copy(address = it)
                        },
                        label = {
                            Text(
                                text = "Enter address",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = viewModel.details.phoneNumber,
                        onValueChange = {
                            viewModel.details = viewModel.details.copy(phoneNumber = it)
                        },
                        label = {
                            Text(
                                text = "Enter phone number",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = viewModel.details.age,
                        onValueChange = { viewModel.details = viewModel.details.copy(age = it) },
                        label = {
                            Text(
                                text = "Enter age",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Select Gender",
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 16.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-8).dp)
                    ) {
                        RadioButton(
                            selected = viewModel.details.gender == "Male",
                            onClick = {
                                viewModel.details = viewModel.details.copy(gender = "Male")
                            }
                        )
                        Text(
                            text = "Male",
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.padding(24.dp))
                        RadioButton(
                            selected = viewModel.details.gender == "Female",
                            onClick = {
                                viewModel.details = viewModel.details.copy(gender = "Female")
                            }
                        )
                        Text(
                            text = "Female",
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.sendOtp(context!!)
                        },
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Sign up",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Already a user?",
                        color = MaterialTheme.colors.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable(onClick = onLoginClick)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OTPVerification(
    phoneNumber: String,
    verifyOtp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "OTP Verification",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Enter OTP sent on $phoneNumber",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
        var otp by remember { mutableStateOf("") }
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = {
                Text(
                    text = "Enter OTP",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = { verifyOtp(otp) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(
                text = "Verify",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
            )
        }
    }
}
