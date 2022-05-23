package com.geekbrains.githubclient.ui.addLogin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.FragmentAddLoginBinding
import com.geekbrains.githubclient.domain.Login

class AddLoginFragment() : AppCompatActivity(), AddLoginContract.View {
    private lateinit var binding: FragmentAddLoginBinding
    lateinit var presenter: AddLoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddLoginBinding.inflate(layoutInflater)
        presenter = AddLoginPresenter(this, applicationContext)
        setContentView(binding.root)
        initSaveButton()
    }

    private fun initSaveButton() {
        binding.addLoginButton.setOnClickListener {
            if (binding.loginEditText.text.toString().isNotEmpty())
                setSuccess()
            else setError("Логин не может быть пустым")
        }
    }

    override fun setSuccess() {
        presenter.onSaveLogin(Login(login = binding.loginEditText.text.toString()))
        onBackPressed()
    }

    override fun setError(error: String) {
        Toast.makeText(
            applicationContext, error, Toast.LENGTH_SHORT
        ).show()
    }

}