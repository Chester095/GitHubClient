package com.geekbrains.githubclient.ui.listLogins

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.app
import com.geekbrains.githubclient.databinding.FragmentListLoginsBinding
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.utils.AppState
import java.util.*

class LoginsFragment : Fragment() {

    private val keyViewModelId = "key_view_model"
    private var _binding: FragmentListLoginsBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy { activity as Controller }
    private lateinit var viewModel: LoginsViewModel
    private val adapter = LoginsRecyclingAdapter { login ->
        controller.openScreen(login)
    }

    interface Controller {
        fun openScreen(login: Login)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is Controller) {
            throw IllegalStateException("Activity должна наследоваться от LoginsFragment.Controller")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListLoginsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginRecyclerView.adapter = adapter

        if (savedInstanceState != null) {
            val viewModelId = savedInstanceState.getString(keyViewModelId)!!
            viewModel = app.viewModelStore.getViewModel(viewModelId) as LoginsViewModel
        } else {
            val id = UUID.randomUUID().toString()
            //TODO а надо ли оно нам
            viewModel = LoginsViewModel(id)
            app.viewModelStore.saveViewModel(viewModel)
        }
        // Подписались на изменения liveData
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            render(state)
        }
        // Запросили новые данные
        viewModel.getLogin()


    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {

                val login: List<Login> = state.data as List<Login>
                adapter.setLogin(login)
            }
            is AppState.Error -> {
                // TODO: 14.04.2022
            }
            is AppState.Loading -> {
                // TODO: 14.04.2022
            }
        }

    }
/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = LoginsPresenter(this, applicationContext)
        binding.recyclerFAB.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    fun initViews(adapterMain: LoginsRecyclingAdapter) {
        binding.loginRecyclerView.adapter = adapterMain
    }

    fun showInsertLoginActivity() {
        startActivity(Intent(this, AddLoginFragment::class.java))
    }

    fun showEditLoginActivity(login: Login) {
        val intent = Intent(this, EditLoginFragment::class.java)
        intent.putExtra("contactId", login.id)
        intent.putExtra("contactLogin", login.login)
        startActivity(intent)
    }

    fun showLoginActivity(login: Login) {
        val intent = Intent(this, LoginFragment::class.java)
        intent.putExtra("contactId", login.id)
        intent.putExtra("contactLogin", login.login)
        startActivity(intent)
    }

    fun areYouSureAlertDialog(login: Login) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Удалить логин")
        builder.setMessage("Вы уверены что хотите удалить логин ${login.login}")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("ДА") { dialog, _ ->
            presenter.deleteContact(login)
            dialog.dismiss()
        }

        builder.setNegativeButton("НЕТ") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
