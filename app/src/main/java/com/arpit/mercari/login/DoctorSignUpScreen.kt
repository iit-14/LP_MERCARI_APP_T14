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
fun DoctorSignUpScreen(
    viewModel: DoctorSignUpViewModel,
    onLoginClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.doctorSignInEvents.collect { event ->
            when (event) {
                NavigateToHome -> navigateToHome()
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
                        painter = painterResource(id = R.drawable.doctor_icon),
                        contentDescription = null,
                        modifier = Modifier.size(84.dp)
                    )
                    OutlinedTextField(
                        value = viewModel.details.name,
                        onValueChange = { viewModel.details = viewModel.details.copy(name = it) },
                        label = {
                            Text(
                                text = "Enter doctor name",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
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
                        value = viewModel.details.experience,
                        onValueChange = {
                            viewModel.details = viewModel.details.copy(experience = it)
                        },
                        label = {
                            Text(
                                text = "Enter years of experience",
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    DropDown(
                        options = listOf("MBBS", "MD"),
                        label = "Select degree",
                        selectedOptionText = viewModel.details.degree,
                        updateSelectedOptionText = {
                            viewModel.details = viewModel.details.copy(degree = it)
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    DropDown(
                        options = listOf(
                            "Dentist",
                            "Dermatologist",
                            "Cardiologist",
                            "Neurologist",
                            "Gynaecologist",
                            "Paediatrician",
                            "Psychiatrist"
                        ),
                        label = "Select medical profession",
                        selectedOptionText = viewModel.details.speciality,
                        updateSelectedOptionText = {
                            viewModel.details = viewModel.details.copy(speciality = it)
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    DropDown(
                        options = listOf(
                            "Hospital XYZ",
                            "Hospital ABC",
                            "Hospital PQR"
                        ),
                        label = "Select hospital",
                        selectedOptionText = viewModel.details.hospital,
                        updateSelectedOptionText = {
                            viewModel.details = viewModel.details.copy(hospital = it)
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = { viewModel.sendOtp(context!!) },
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.padding(top = 24.dp)
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
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DropDown(
    options: List<String>,
    label: String,
    selectedOptionText: String,
    updateSelectedOptionText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = {
                Text(
                    text = label,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        updateSelectedOptionText(selectionOption)
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}
