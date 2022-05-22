package com.geekbrains.githubclient.ui.openLogin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubclient.app
import com.geekbrains.githubclient.databinding.FragmentListLoginsBinding
import com.geekbrains.githubclient.domain.Login

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentListLoginsBinding

    companion object {
        private const val USER_ARGS_KEY = "USER"
        fun newInstance(login: Login) = LoginFragment().apply {
            arguments = Bundle()
            arguments?.putParcelable(USER_ARGS_KEY, login)
        }
    }

    // подключаем гугловский вьюмодель
    private val viewModel: ReposViewModel by viewModels { ReposViewModelFactory(app.gitProjectsRepo) }
    private val adapter = ProjectsRecyclingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListLoginsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentContact()
        initViews()


    }

    private fun getIntentContact() {
        val intent = intent
        val contactId = intent.getIntExtra("contactId", 0)
        val contactLogin = intent.getStringExtra("contactLogin")
        contactLogin?.let {
            setTextView(it)
            initOutgoingEvents(it)
        }
        initIncomingEvents()

    }

    private fun setTextView(contactLogin: String?) {
        binding.headerLoginTextView.text = contactLogin
    }


    private fun initViews() {
        binding.gitProjectsRecyclerView.layoutManager = LinearLayoutManager(this)
        // приложение само начнёт считать
        adapter.setHasStableIds(true)
        binding.gitProjectsRecyclerView.adapter = adapter
    }


    private fun initOutgoingEvents(contactLogin: String) {
        // передаём в onShowRepos contactLogin
        viewModel.onShowRepos(contactLogin)

    }

    private fun initIncomingEvents() {
        // подписываемся на вьюмодель
        viewModel.repos.observe(this) {
            // передаём в адаптер те данные, которые пришли
            // обновляем каждый раз адаптер, когда к нам приходят новые данные
            adapter.setData(it)
        }
        // подписываемся на состояние загрузки
        viewModel.inProgress.observe(this) { inProgress ->
            binding.progressBarLayout.isVisible = inProgress
        }
    }

}