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

class DoctorLoginFragment: Fragment() {

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
                    DoctorLoginScreen(
                        viewModel = viewModel,
                        onSignUpClick = {
                            val directions = DoctorLoginFragmentDirections
                                .actionDoctorLoginFragmentToDoctorSignUpFragment()
                            findNavController().navigate(directions)
                        },
                        navigateToHome = {
                            val directions = DoctorLoginFragmentDirections
                                .actionGlobalHomeFragment()
                            findNavController().navigate(directions)
                        }
                    )
                }
            }
        }
    }

}