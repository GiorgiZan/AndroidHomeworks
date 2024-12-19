package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.androidhomeworks.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : Fragment() {
    private var binding:FragmentSignUpBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpOnClick()
    }


    private fun signUpOnClick(){
        binding!!.btnSignUp.setOnClickListener{
            if (!userEmailValidations()){
                return@setOnClickListener
            }
            if (!userPasswordValidations()){
                return@setOnClickListener
            }
            binding?.root?.showSnackBar("Great success!", R.color.gray)
            parentFragmentManager.popBackStack()
        }
    }


    private fun userEmailValidations(): Boolean{
        if (binding?.etEmail?.text.toString().trim().isEmpty()){
            binding?.etEmail?.showSnackBar(getString(R.string.email_empty), R.color.red)
            return false
        }
        else if (!binding?.etEmail?.text.toString().trim().contains(getString(R.string.email_symbol))){
            binding?.etEmail?.showSnackBar(getString(R.string.email_invalid), R.color.red)
            return false
        }
        else if (binding?.etEmail?.text.toString().trim().length < 7 || binding?.etEmail?.text.toString().trim().length >= 35 ){
            binding?.etEmail?.showSnackBar(getString(R.string.email_length), R.color.red)
            return false
        }

        return true
    }

    private fun userPasswordValidations(): Boolean{
        if (binding?.etPassword?.text.toString().trim().isEmpty()){
            binding?.etPassword?.showSnackBar(getString(R.string.password_empty), R.color.red)
            return false
        }
        if (binding?.etPassword?.text.toString().trim().length <= 5){
            binding?.etPassword?.showSnackBar(getString(R.string.password_length), R.color.red)
            return false
        }
        return true
    }


    private fun View.showSnackBar(messageToShow:String, backgroundColor: Int){
        val snackBar = Snackbar.make(this, messageToShow, Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(ContextCompat.getColor(context, backgroundColor))
        snackBar.show()
    }

}