package com.mobile.dufunatask.auth.presentation

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mobile.dufunatask.R
import com.mobile.dufunatask.databinding.FragmentSigninBinding
import com.mobile.dufunatask.utils.displaySnack
import com.mobile.dufunatask.utils.displayToast
import com.mobile.dufunatask.utils.hideKeyboard
import com.mobile.dufunatask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_signin) {
    private val signInViewModel: SignInViewModel by viewModels()
    private val binding by viewBinding(FragmentSigninBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() = with(binding) {
        setUpFormatString()
        signInUI()
        loginUsernameField.validateUsername()
        loginPasswordField.validatePassword()
    }

    private fun setUpObservers() = with(signInViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                navAction.collectLatest {
                    when (it) {
                        is SignInNavigationEvent.Navigate -> findNavController().navigate(it.destination)
                    }
                }
            }
        }

        lifecycleScope.launch {
            isSignInEnable.collect {
                binding.signIn.isEnabled = it
                signInUI()
            }
        }

        binding.apply {
            signInUserState.observe(viewLifecycleOwner) {
                when (it) {
                    is Result.Success -> {
                        requireContext().displayToast(it.data?.message)
                        Timber.d(it.data.toString())
                    }

                    is Result.Error -> root.displaySnack(requireContext(), it.message)

                    is Result.Loading -> it.state.second?.let { error ->
                        handleViewState(
                            viewState = it.state.first,
                            textState = error
                        )
                    }
                }
            }
        }
    }

    private fun signInUI() = with(binding) {
        val username = loginUsernameField.text.toString()
        val password = loginPasswordField.text.toString()
        signIn.handleSignIn(username, password)
    }

    private fun handleViewState(viewState: Boolean, textState: String) = with(binding) {
        signIn.text = textState
        signIn.isEnabled = viewState
    }

    private fun setUpFormatString() = with(binding) {
        contactSupport.text = getString(R.string.no_account).formatStringsFromXml(22, 37)
        termsAndAgreement.text = getString(R.string.privacy_policy).formatStringsFromXml(50, 86)
    }

    private fun String.formatStringsFromXml(start: Int, stop: Int) =
        SpannableString(this@formatStringsFromXml).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.light_blue
                    )
                ), start, stop, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        }

    private fun Button.handleSignIn(username: String, password: String) {
        setOnClickListener {
            signInViewModel.signIn(username, password)
            requireContext().hideKeyboard(it)
        }
    }

    private fun EditText.validateUsername() = with(signInViewModel) {
        addTextChangedListener { setUsername(it.toString()) }
    }

    private fun EditText.validatePassword() = with(signInViewModel) {
        addTextChangedListener { setPassword(it.toString()) }
    }
}