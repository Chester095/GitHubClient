package com.geekbrains.githubclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.ui.listLogins.LoginsFragment
import com.geekbrains.githubclient.ui.openLogin.LoginFragment

class MainActivity : AppCompatActivity(), LoginsFragment.Controller {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val loginsFragment: Fragment = LoginsFragment()
            supportFragmentManager
                .beginTransaction()
                .add(binding.mainContainer.id, loginsFragment)
                .commit()
        }
    }

    override fun openScreen(conact: Contact) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.push_up_in,
                R.anim.push_up_out,
                R.anim.slide_out
            )
            .addToBackStack(null)
            .replace(
                binding.mainContainer.id,
                LoginFragment.newInstance(conact)
            )
            .commit()
    }
}