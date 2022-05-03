package com.geekbrains.githubclient.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        presenter = LoginPresenter(this, applicationContext)
        setContentView(binding.root)

        val intent = intent
        val contactId = intent.getIntExtra("contactId", 0)
        val contactLogin = intent.getStringExtra("contactLogin")

        setTextView(contactLogin)
    }

    override fun setTextView(contactLogin: String?) {
        binding.headerLoginTextView.text = contactLogin
    }

    override fun initViews(adapterLogin: LoginRecyclingAdapter) {
        TODO("Not yet implemented")
    }


    override fun setSuccess() {
        Toast.makeText(
            applicationContext, "Данные загрузились", Toast.LENGTH_SHORT
        ).show()
    }

    override fun setError(error: String) {
        Toast.makeText(
            applicationContext, error, Toast.LENGTH_SHORT
        ).show()
    }

}