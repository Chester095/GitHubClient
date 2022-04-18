package com.geekbrains.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.ui.recycler.RecyclerFragment

class MainActivity : AppCompatActivity(), NavigationActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeGreen)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigationTo(RecyclerFragment.newInstance())
        }
    }

    override fun navigationTo(fragment: Fragment, withTransaction: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.push_up_out,
                R.anim.push_up_in,
                R.anim.slide_out
            )
            .replace(R.id.container, fragment)

        if (withTransaction) {
            transaction.addToBackStack("Transaction")
        }
        transaction.commit()

    }
}