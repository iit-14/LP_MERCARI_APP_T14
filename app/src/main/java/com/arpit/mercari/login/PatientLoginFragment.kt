package com.arpit.mercari.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.arpit.mercari.compose.theme.MercariTheme

class PatientLoginFragment : Fragment() {

    private val viewModel by viewModels<PatientSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MercariTheme {
                    PatientLoginScreen(
                        viewModel = viewModel,
                        onSignUpClick = {
                            val directions = PatientLoginFragmentDirections
                                .actionPatientLoginFragmentToPatientSignUpFragment()
                            findNavController().navigate(directions)
                        },
                        navigateToHome = {
                            val directions = PatientLoginFragmentDirections
                                .actionGlobalHomeFragment()
                            findNavController().navigate(directions)
                        }
                    )
                }
            }
        }
    }
}