package com.codebros.eripple.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityMainBinding
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.main.eripple_info.ErippleInfoFragment
import com.codebros.eripple.screen.main.history.HistoryFragment
import com.codebros.eripple.screen.main.home.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() = with(binding){

        bottomNav.setOnItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)

       //binding.container.set
    }

    private fun showFragment(fragment: Fragment, tag : String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commitAllowingStateLoss()
        }

        //binding.titleTxv.text =
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            R.id.history -> {
                showFragment(HistoryFragment.newInstance(), HistoryFragment.TAG)
                true
            }

            R.id.eripple_info -> {
                showFragment(ErippleInfoFragment.newInstance(), ErippleInfoFragment.TAG)
                true
            }

            R.id.my_point -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            R.id.setting -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            else -> {
                false
            }

        }
    }

}