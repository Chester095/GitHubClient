package com.geekbrains.githubclient.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.app
import com.geekbrains.githubclient.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    // подключаем гугловский вьюмодель
    private val viewModel: ReposViewModel by viewModels { ReposViewModelFactory(app.gitProjectsRepo) }
    private val adapter = LoginRecyclingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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