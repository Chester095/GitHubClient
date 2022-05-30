package com.geekbrains.githubclient.ui.openLogin

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.app
import com.geekbrains.githubclient.databinding.FragmentOpenLoginBinding
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import org.koin.android.ext.android.inject
import java.util.*

class LoginFragment : Fragment() {

    companion object {
        private const val LOGIN_ARGS_KEY = "LOGIN"
        fun newInstance(login: Login) = LoginFragment().apply {
            arguments = Bundle()
            arguments?.putParcelable(LOGIN_ARGS_KEY, login)
        }
    }

    private val keyViewModelId = "key_card_view_model"
    private var _binding: FragmentOpenLoginBinding? = null
    private val binding get() = _binding!!
    private val adapter = ProjectsRecyclingAdapter()
    private lateinit var viewModel: ReposViewModel
    private val repo: ProjectsRepo by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOpenLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactLogin = arguments?.getParcelable<Login>("LOGIN")
        binding.gitProjectsRecyclerView.adapter = adapter

        if (savedInstanceState != null) {
            val viewModelId = savedInstanceState.getString(keyViewModelId)!!
            viewModel = app.viewModelStore.getViewModel(viewModelId) as ReposViewModel
        } else {
            val id = UUID.randomUUID().toString()
            viewModel = ReposViewModel(id, repo)
            app.viewModelStore.saveViewModel(viewModel)
        }

        // Подписались на изменения liveData
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            render(state)
        }

        // Запросили новые данные
        contactLogin?.let {
            binding.headerLoginTextView.text = it.login
            viewModel.getProjectsRetrofit(it.login) }



    }
    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val project: List<GitProjectEntity> = state.data as List<GitProjectEntity>
                adapter.setData(project)
            }
            is AppState.Error -> {
                Log.d("!!!","AppState.Error")
            }
            is AppState.Loading -> {
                Log.d("!!!","AppState.Loading")
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



