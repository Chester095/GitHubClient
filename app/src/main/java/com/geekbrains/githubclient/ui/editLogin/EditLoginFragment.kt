package com.geekbrains.githubclient.ui.editLogin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.FragmentEditLoginBinding
import com.geekbrains.githubclient.domain.Login


class EditLoginFragment() : AppCompatActivity(), EditLoginContract.View {
    private lateinit var binding: FragmentEditLoginBinding
    lateinit var presenter: EditLoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = FragmentEditLoginBinding.inflate(layoutInflater)
        presenter = EditLoginPresenter(this, applicationContext)
        setContentView(binding.root)

        val intent = intent
        val contactId = intent.getIntExtra("contactId", 0)
        val contactLogin = intent.getStringExtra("contactLogin")

        setTextView(contactLogin)
        initSaveButton(contactId)
    }

    private fun initSaveButton(contactId: Int) {
        binding.addLoginButton.setOnClickListener {
            if (binding.loginEditText.text.toString().isNotEmpty())
                setSuccess(contactId)
            else setError("Логин не может быть пустым")
        }
    }

    override fun setTextView(contactLogin: String?) {
        binding.loginEditText.setText(contactLogin)
    }

    override fun setSuccess(contactId: Int) {
        val loginIn = Login(
            contactId,
            binding.loginEditText.text.toString()
        )
        presenter.updateContact(loginIn)
        onBackPressed()
    }

    override fun setError(error: String) {
        Toast.makeText(
            applicationContext, error, Toast.LENGTH_SHORT
        ).show()
    }

}