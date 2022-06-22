package com.electricarandroidapp.ui.fragments.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.electricarandroidapp.api.RetrofitInstance
import com.electricarandroidapp.repository.RegisterRepository
import com.electricarandroidapp.databinding.FragmentRegisterBinding
import com.electricarandroidapp.ui.fragments.base.BaseFragment
import com.electricarandroidapp.viewmodel.login.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding, RegisterRepository>() {


    private lateinit var nameEditText : TextInputEditText
    private lateinit var surnameEditText : TextInputEditText
    private lateinit var phoneEditText : TextInputEditText
    private lateinit var emailEditText : TextInputEditText
    private lateinit var passwordEditText : TextInputEditText
    private lateinit var confirmPasswordEditText : TextInputEditText
    private lateinit var alertDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        observe()
    }

    private fun observe() {
        viewModel.registerResponse.observe(viewLifecycleOwner, { registerResponse ->
            if (registerResponse.failed || !registerResponse.succeeded) {
                showMessage(this.requireContext(), "Register failed. Please Try Again.")
                return@observe
            }
            else {
                alertDialog.show()
            }
        })
    }

    private fun openLoginScreen() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginUsernamePasswordFragment()
        findNavController().navigate(action)
        alertDialog.dismiss()
    }

    override fun setup() {
        bindViews()
        onRegisterButtonClickListener()
        onLoginHereButtonClick()
        initializeAlertDialog()
    }

    private fun bindViews() {
       nameEditText = binding.nameEditText
       surnameEditText = binding.surnameEditText
       phoneEditText = binding.phoneEditText
       emailEditText = binding.emailEditText
       passwordEditText = binding.passwordEditText
    }

    private fun onRegisterButtonClickListener() {
        binding.registerButton.setOnClickListener {
            if(validateRegisterFields()) {
                val name = nameEditText.text.toString()
                val surname = surnameEditText.text.toString()
                val phone = phoneEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                viewModel.registerUser(email, password, name, surname, phone)
            }

        }
    }

    private fun onLoginHereButtonClick() {
        binding.loginHere.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginUsernamePasswordFragment()
            findNavController().navigate(action)
        }
    }

    private fun initializeAlertDialog() {
        alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("")
            .setMessage("Register is successful")
            .setPositiveButton(("Continue")) { dialogInterface, i -> openLoginScreen() }
            .create()
    }

    private fun validateRegisterFields() : Boolean {
        var valid = true
        if (nameEditText.text.isNullOrEmpty()) {
            nameEditText.error = "Required Field"
            valid = valid && false
        }else{
            valid = valid && true
        }

        if (surnameEditText.text.isNullOrEmpty()) {
            surnameEditText.error = "Required Field"
            valid = valid && false
        }else{
            valid = valid && true
        }

        if (phoneEditText.text.isNullOrEmpty()) {
            phoneEditText.error = "Required Field"
            valid = valid && false
        }else{
            valid = valid && true
        }

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

        if (confirmPasswordEditText.text.isNullOrEmpty()) {
            confirmPasswordEditText.error = "Required Field"
            valid = valid && false
        }else{
            valid = valid && true
        }

        if (confirmPasswordEditText.text.toString()  != passwordEditText.text.toString()) {
            confirmPasswordEditText.error = "Passwords do not match"
            valid = valid && false
        }else{
            valid = valid && true
        }

        return valid
    }

    override fun getViewModel(): Class<RegisterViewModel>
        = RegisterViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRegisterBinding
        = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): RegisterRepository
        = RegisterRepository(RetrofitInstance.apiService)



}