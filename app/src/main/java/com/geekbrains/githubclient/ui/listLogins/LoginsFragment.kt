package com.geekbrains.githubclient.ui.listLogins

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.ui.addLogin.AddLoginFragment
import com.geekbrains.githubclient.ui.editLogin.EditLoginFragment
import com.geekbrains.githubclient.ui.openLogin.LoginFragment

class LoginsFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    lateinit var presenter: LoginsContract.Presenter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginsPresenter(this, applicationContext)
        binding.recyclerFAB.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    private val controller by lazy { activity as Controller }

    interface Controller {
        fun openScreen(user: User)
    }

    override fun initViews(adapterMain: LoginsRecyclingAdapter) {
        binding.loginRecyclerView.adapter = adapterMain
    }

    override fun showInsertLoginActivity() {
        startActivity(Intent(this, AddLoginFragment::class.java))
    }

    override fun showEditLoginActivity(contact: Contact) {
        val intent = Intent(this, EditLoginFragment::class.java)
        intent.putExtra("contactId", contact.id)
        intent.putExtra("contactLogin", contact.login)
        startActivity(intent)
    }

    override fun showLoginActivity(contact: Contact) {
        val intent = Intent(this, LoginFragment::class.java)
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
