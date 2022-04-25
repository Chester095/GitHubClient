package com.geekbrains.githubclient.ui.editlogin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityEditLoginBinding
import com.geekbrains.githubclient.ui.Contact

class EditLoginActivity(contact: Contact) : AppCompatActivity(), EditLoginContract.View {
    private lateinit var binding: ActivityEditLoginBinding
    lateinit var presenter: EditLoginContract.Presenter
    var contact = contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = ActivityEditLoginBinding.inflate(layoutInflater)
        presenter = EditLoginPresenter(this, applicationContext)
        setContentView(binding.root)
        setTextView()
        initSaveButton()
    }

    private fun initSaveButton() {
        binding.addLoginButton.setOnClickListener {
            if (binding.loginEditText.text.toString().isNotEmpty())
                setSuccess()
            else setError("Логин не может быть пустым")
        }
    }

    override fun setTextView() {
        binding.loginEditText.setText(contact.login)
    }

    override fun setSuccess() {
        val contactIn = Contact(
            contact.id,
            binding.loginEditText.text.toString()
        )
        presenter.updateContact(contactIn)
        onBackPressed()
    }

    override fun setError(error: String) {
        Toast.makeText(
            applicationContext, error, Toast.LENGTH_SHORT
        ).show()
    }

}