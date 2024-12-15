package com.example.androidhomeworks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()



        listeners()
    }


    private fun listeners(){
        binding.ivBack.setOnClickListener{
            if (binding.btnSignUp.visibility == View.VISIBLE){
                backToEmailAndPassword()
                return@setOnClickListener
            }
            finish()
        }

        binding.btnNext.setOnClickListener{
            if (!validateEmailField()){
                return@setOnClickListener
            }
            if (!validatePasswordField()){
                return@setOnClickListener
            }
            registerStepTwo()
        }

        binding.btnSignUp.setOnClickListener{
            if (!validateUsernameField()){
                return@setOnClickListener
            }
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            createUserWithEmailAndPassword(email, password)

        }

    }


    private fun validateEmailField(): Boolean{
        if (binding.etEmail.text!!.isEmpty()){
            binding.etEmail.error = getString(R.string.email_empty)
            return false
        }
        else if (!binding.etEmail.text!!.contains(getString(R.string.email_symbol))){
            binding.etEmail.error = getString(R.string.email_not_valid)
            return false
        }
        else if (binding.etEmail.text!!.length < 7 || binding.etEmail.text!!.length >= 35){
            binding.etEmail.error = getString(R.string.email_length)
            return false
        }

        return true
    }

    private fun validatePasswordField(): Boolean{
        if (binding.etPassword.text!!.isEmpty()){
            binding.etPassword.error = getString(R.string.password_empty)
            return false
        }
        else if (binding.etPassword.text!!.length < 5){
            binding.etPassword.error = getString(R.string.password_length)
            return false
        }

        return true
    }

    private fun registerStepTwo(){
        with(binding){
            etEmail.visibility = View.GONE
            etPassword.visibility = View.GONE
            btnNext.visibility = View.GONE
            etUsername.visibility = View.VISIBLE
            tvTermsAndPrivacy.visibility = View.VISIBLE
            btnSignUp.visibility = View.VISIBLE
        }
    }

    private fun validateUsernameField(): Boolean{
        if (binding.etUsername.text!!.isEmpty()){
            binding.etUsername.error = getString(R.string.username_empty)
            return false
        }

        return true
    }

    private fun backToEmailAndPassword(){
        with(binding){
            etEmail.visibility = View.VISIBLE
            etPassword.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
            etUsername.visibility = View.GONE
            tvTermsAndPrivacy.visibility = View.GONE
            btnSignUp.visibility = View.GONE
        }
    }

    private fun createUserWithEmailAndPassword(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this,getString(R.string.registration_successful, user?.email), Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    Toast.makeText(this, getString(R.string.registration_failed, task.exception?.message), Toast.LENGTH_SHORT).show()
                }
            }

    }


}