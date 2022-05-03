package com.geekbrains.githubclient.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.ui.addlogin.AddLoginActivity
import com.geekbrains.githubclient.ui.editlogin.EditLoginActivity
import com.geekbrains.githubclient.ui.login.LoginActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    lateinit var presenter: MainContract.Presenter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this, applicationContext)
        binding.recyclerFAB.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    override fun initViews(adapterMain: MainRecyclingAdapter) {
        binding.recyclerView.adapter = adapterMain
    }

    override fun showInsertLoginActivity() {
        startActivity(Intent(this, AddLoginActivity::class.java))
    }

    override fun showEditLoginActivity(contact: Contact) {
        val intent = Intent(this, EditLoginActivity::class.java)
        intent.putExtra("contactId", contact.id)
        intent.putExtra("contactLogin", contact.login)
        startActivity(intent)
    }

    override fun showLoginActivity(contact: Contact) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("contactId", contact.id)
        intent.putExtra("contactLogin", contact.login)
        startActivity(intent)
    }

    override fun areYouSureAlertDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Удалить логин")
        builder.setMessage("Вы уверены что хотите удалить логин ${contact.login}")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("ДА") { dialog, _ ->
            presenter.deleteContact(contact)
            dialog.dismiss()
        }

        builder.setNegativeButton("НЕТ") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            .show()
    }
}
