package com.electricarandroidapp.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.electricarandroidapp.databinding.FragmentLoginUsernamePasswordBinding
import com.electricarandroidapp.api.RetrofitInstance
import com.electricarandroidapp.repository.LoginRepository
import com.electricarandroidapp.ui.activities.MainActivity
import com.electricarandroidapp.ui.fragments.base.BaseFragment
import com.electricarandroidapp.viewmodel.login.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginUsernamePasswordFragment : BaseFragment<LoginViewModel, FragmentLoginUsernamePasswordBinding, LoginRepository>() {

    private lateinit var emailEditText : TextInputEditText
    private lateinit var passwordEditText : TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        setup()
    }

    private fun observe() {
        viewModel.loginResponse.observe(viewLifecycleOwner){ response ->
            if(response.failed || !response.succeeded){
                showMessage(this.requireContext(), "Login failed. Please Try again.")
                return@observe
            }

            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this.requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    override fun setup() {
        bindViews()
        onLoginButtonClickListener()
        onRegisterHereButtonClickListener()
    }

    private fun bindViews() {
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText
    }

    private fun onRegisterHereButtonClickListener() {
        binding.registerHere.setOnClickListener{
            val action = LoginUsernamePasswordFragmentDirections.actionLoginUsernamePasswordFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun onLoginButtonClickListener() {
        binding.loginButton.setOnClickListener {
            if(validateFields()){
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                viewModel.login(email, password)
            }
        }
    }

    private fun validateFields(): Boolean {
        var valid = true

        if (emailEditText.text.isNullOrEmpty()) {
            emailEditText.error = "Required Field"
            valid = valid && false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text!!).matches()){
            emailEditText.error = "Invalid email format"
            valid = valid && false
        } else{
            valid = valid && true
        }

        if (passwordEditText.text.isNullOrEmpty()) {
            passwordEditText.error = "Required Field"
            valid = valid && false
        }else{
            valid = valid && true
        }

        return valid
    }

    override fun getViewModel(): Class<LoginViewModel>
        = LoginViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginUsernamePasswordBinding
        = FragmentLoginUsernamePasswordBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): LoginRepository
        = LoginRepository(RetrofitInstance.apiService)




}