package com.geekbrains.githubclient.ui.listLogins

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.app
import com.geekbrains.githubclient.databinding.FragmentListLoginsBinding
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import org.koin.android.ext.android.inject
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

    private val repo: ProjectsRepo by inject()

    interface Controller {
        fun openScreen(login: Login)
        fun addLogin()
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
//        binding.recyclerFAB.setOnClickListener { controller.addLogin() }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginRecyclerView.adapter = adapter

        if (savedInstanceState != null) {
            Log.d("!!!", "savedInstanceState=")
            val viewModelId = savedInstanceState.getString(keyViewModelId)
            viewModel = viewModelId?.let { app.viewModelStore.getViewModel(it) } as LoginsViewModel
        } else {
            val id = UUID.randomUUID().toString()
            viewModel = LoginsViewModel(id, repo)
            app.viewModelStore.saveViewModel(viewModel)
        }
        // Подписались на изменения liveData
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            render(state)
        }

        // Запросили новые данные
        viewModel.getLogin(requireContext())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(keyViewModelId, viewModel.id)
    }



    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                Log.d("!!!","Logins  -  AppState.Success")
                val login: List<Login> = state.data as List<Login>
                adapter.setLogin(login)
            }
            is AppState.Error -> {
                Log.d("!!!","Logins  -  AppState.Error")
            }
            is AppState.Loading -> {
                Log.d("!!!","Logins  -  AppState.Loading")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
