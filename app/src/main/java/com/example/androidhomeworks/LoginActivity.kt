package com.example.androidhomeworks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        listeners()
    }


    private fun listeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnLogIn.setOnClickListener {
            if (!validateEmailField()) {
                return@setOnClickListener
            }
            if (!validatePasswordField()) {
                return@setOnClickListener
            }
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            signInWithEmailAndPassword(email, password)


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

    private fun signInWithEmailAndPassword(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, getString(R.string.login_successful, user?.email), Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.login_failed, task.exception?.message), Toast.LENGTH_SHORT).show()
                }
            }
    }

}