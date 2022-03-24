@file:Suppress("PrivatePropertyName")

package com.arpit.mercari.login

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arpit.mercari.data.model.DoctorSignUpRequest
import com.arpit.mercari.data.service.authService
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class DoctorSignUpViewModel : ViewModel() {

    private val doctorSignInEventsChannel = Channel<DoctorSignInEvents>()
    val doctorSignInEvents = doctorSignInEventsChannel.receiveAsFlow()

    var loginNumber by mutableStateOf("")
    var loginScreen = false

    var details by mutableStateOf(DoctorSignUpDetails())
    var otpScreen by mutableStateOf(false)
    var progressBarText by mutableStateOf<String?>(null)
        private set

    private val RESEND_SMS_WAIT_TIME = 60L

    private var storedVerificationId = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                viewModelScope.launch {
                    signInWithPhoneAuthCredential(credential)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressBarText = null
                Log.e("TAG", "verification failed", e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                viewModelScope.launch {
                    progressBarText = null
                    otpScreen = true
                    storedVerificationId = verificationId
                    resendToken = token
                }
            }
        }

    private fun getPhoneAuthOptionsBuilder(
        activity: Activity,
        phone: String
    ): PhoneAuthOptions.Builder {
        return PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber("+91$phone")
            .setTimeout(RESEND_SMS_WAIT_TIME, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks!!)
    }

    fun sendOtp(activity: Activity) {
        progressBarText = "Sending otp..."
        val phone = if (loginScreen) loginNumber else details.phoneNumber
        val options = getPhoneAuthOptionsBuilder(activity, phone)
        PhoneAuthProvider.verifyPhoneNumber(options.build())
    }

    fun signInUsingOTP(otp: String) {
        progressBarText = "Signing in..."
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp)
        viewModelScope.launch {
            signInWithPhoneAuthCredential(credential)
        }
    }

    private suspend fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        try {
            Firebase.auth.signInWithCredential(credential).await()
            Log.e("TAG", "success $details")
            if (loginScreen) {
                authService.loginDoctor(Firebase.auth.uid!!)
            } else {
                val request = DoctorSignUpRequest(
                    Name = details.name,
                    TokenID = Firebase.auth.uid!!,
                    HospitalID =123,
                    Degree = details.degree,
                    Speciality = details.speciality,
                    Number = details.phoneNumber.toLong(),
                    Experience = details.experience.toLong()
                )
                authService.registerDoctor(request)
            }
            doctorSignInEventsChannel.send(NavigateToHome)
        } catch (e: Exception) {
            Log.e("TAG", "sign in failed", e)
            Firebase.auth.signOut()
        }
        progressBarText = null
    }

}

data class DoctorSignUpDetails(
    val name: String = "",
    val phoneNumber: String = "",
    val experience: String = "",
    val degree: String = "",
    val speciality: String = "",
    val hospital: String = ""
)

sealed interface DoctorSignInEvents
object NavigateToHome: DoctorSignInEvents