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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorSignUpFragment : Fragment() {

    val viewModel by viewModels<DoctorSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MercariTheme {
                    DoctorSignUpScreen(
                        viewModel = viewModel,
                        onLoginClick = {
                            val directions = DoctorSignUpFragmentDirections
                                .actionDoctorSignUpFragmentToDoctorLoginFragment()
                            findNavController().navigate(directions)
                        },
                        navigateToHome = {
                            val directions = DoctorSignUpFragmentDirections
                                .actionGlobalHomeFragment()
                            findNavController().navigate(directions)
                        }
                    )
                }
            }
        }
    }
}