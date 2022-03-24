package com.arpit.mercari.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.arpit.mercari.compose.theme.MercariTheme

class PreLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MercariTheme {
                    PreLoginScreen(
                        onPatientClick = {
                            val directions = PreLoginFragmentDirections
                                    .actionPreLoginFragmentToPatientSignUpFragment()
                            findNavController().navigate(directions)
                        },
                        onDoctorClick = {
                            val directions = PreLoginFragmentDirections
                                .actionPreLoginFragmentToDoctorSignUpFragment()
                            findNavController().navigate(directions)
                        }
                    )
                }
            }
        }
    }

}