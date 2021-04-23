package com.mindorks.example.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mindorks.example.R
import com.mindorks.example.R.layout
import com.mindorks.example.ui.fragment.ItemsFragment
import com.mindorks.example.ui.fragment.SalesFragment
import com.mindorks.example.ui.fragment.SettingsFragment

import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

class MainActivity : AppCompatActivity() {

    private val itemsFragment = ItemsFragment()
    private val salesFragment = SalesFragment()
    private val settingsFragment = SettingsFragment()
    private val fragmentManager = supportFragmentManager 
    private var activeFragment: Fragment = itemsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        fragmentManager.beginTransaction().apply {
            add(R.id.container, settingsFragment, getString(R.string.user)).hide(settingsFragment)
            add(R.id.container, salesFragment, getString(R.string.after_academy)).hide(salesFragment)
            add(R.id.container, itemsFragment, getString(R.string.mindOrks))
        }.commit()
        initListeners()
        bottomNavigationView.itemIconTintList = null
    }

    private fun initListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_items -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(itemsFragment).commit()
                    activeFragment = itemsFragment
                    true
                }

                R.id.navigation_sales -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(salesFragment).commit()
                    activeFragment = salesFragment
                    true
                }

                R.id.navigation_settings -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(settingsFragment).commit()
                    activeFragment = settingsFragment
                    true
                }

                else -> false
            }
        }
    }
}
