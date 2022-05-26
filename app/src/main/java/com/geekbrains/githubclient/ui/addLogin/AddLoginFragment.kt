package com.geekbrains.githubclient.ui.addLogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.FragmentAddLoginBinding
import com.geekbrains.githubclient.databinding.FragmentOpenLoginBinding
import com.geekbrains.githubclient.domain.Login

class AddLoginFragment() : Fragment(), AddLoginContract.View {
    lateinit var presenter: AddLoginContract.Presenter

    private var _binding: FragmentAddLoginBinding? = null
    private val binding: FragmentAddLoginBinding
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance(): AddLoginFragment {
            return AddLoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddLoginBinding.inflate(inflater, container, false)
        return binding.root
        presenter = AddLoginPresenter(this, requireContext())
        initSaveButton()
    }



    private fun initSaveButton() {
        binding.addLoginButton.setOnClickListener {
            if (binding.loginEditText.text.toString().isNotEmpty())
                setSuccess()
            else setError("Логин не может быть пустым")
        }
    }

    override fun setSuccess() {
        presenter.onSaveLogin(Login(login = binding.loginEditText.text.toString()))
    }

    override fun setError(error: String) {
        Toast.makeText(
            requireContext(), error, Toast.LENGTH_SHORT
        ).show()
    }

}