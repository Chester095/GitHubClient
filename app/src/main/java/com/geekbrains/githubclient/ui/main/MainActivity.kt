package com.geekbrains.githubclient.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.databinding.DialogEditBinding
import com.geekbrains.githubclient.ui.*
import com.geekbrains.githubclient.ui.addlogin.AddLoginActivity

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
/*        presenter.insertLogin(Contact(login = "123"))
        presenter.insertLogin(Contact(login = "fvrth"))
        presenter.insertLogin(Contact(login = "dfndfgjsf"))*/
        Log.d("!!!", "presenter = " + presenter)
        binding.recyclerFAB.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    override fun initViews(adapter: RecyclingAdapter) {
        binding.recyclerView.adapter = adapter
/*        rv = findViewById(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter*/
    }

    override fun showInsertLoginActivity() {
        Log.d("!!!", "presenter = $presenter")
        startActivity(Intent(this, AddLoginActivity::class.java))
    }

    override fun showOpenLoginActivity(contact: Contact) {
        lateinit var binding: DialogEditBinding
        val editDialog = Dialog(this, R.style.Theme_Dialog)
        editDialog.setCancelable(false)
        editDialog.setContentView(R.layout.dialog_edit)

        val etEditName = binding.etName

        etEditName.setText(contact.login)

        binding.tvInsertEdited.setOnClickListener {
            val contactIn = Contact(
                contact.id,
                etEditName.text.toString()
            )

            presenter.updateContact(contactIn)
            editDialog.dismiss()
        }

        binding.tvCancel.setOnClickListener {
            editDialog.dismiss()
        }
        editDialog.show()
    }

    override fun areYouSureAlertDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete Contact")
        builder.setMessage("Are you sure you wants to delete ${contact.login}")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("YES") { dialog, _ ->
            presenter.deleteContact(contact)
            dialog.dismiss()
        }

        builder.setNegativeButton("NO") { dialog, _ ->
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