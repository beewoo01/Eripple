package com.codebros.eripple.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityMainBinding
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.main.eripple_info.ErippleInfoFragment
import com.codebros.eripple.screen.main.history.HistoryFragment
import com.codebros.eripple.screen.main.home.HomeFragment
import com.codebros.eripple.screen.main.my_point.MyPointFragment
import com.codebros.eripple.screen.main.setting.SettingFragment
import com.codebros.eripple.util.KeepStateNavigator
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity()/*<MainViewModel, ActivityMainBinding>(),
    NavigationBarView.OnItemSelectedListener*/ {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /*override val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.fragmentStatus.observe(this@MainActivity) {
        when (it) {
            R.id.home -> {
                //showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
            }

            R.id.history -> {
                //showFragment(HistoryFragment.newInstance(), HistoryFragment.TAG)
            }

            R.id.eripple_info -> {
                //showFragment(ErippleInfoFragment.newInstance(), ErippleInfoFragment.TAG)
            }

            R.id.my_point -> {
                //showFragment(MyPointFragment.newInstance(), MyPointFragment.TAG)
            }

            R.id.setting -> {
                //showFragment(SettingFragment.newInstance(), SettingFragment.TAG)
            }

            else -> {

            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }


    private fun initViews() {
        with(binding) {
            /*bottomNav.setOnItemSelectedListener(this@MainActivity)
            showFragment(HomeFragment.newInstance(), HomeFragment.TAG)*/

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
            val navController = navHostFragment.navController

            // KeepStateNavigator navController에 추가
            val navigator = KeepStateNavigator(
                this@MainActivity,
                navHostFragment.childFragmentManager,
                R.id.container
            )
            navController.navigatorProvider.addNavigator(navigator)

            navController.setGraph(R.navigation.nav_graph)
            // 바텀 네비게이션 뷰와 navController 연결

            bottomNav.setupWithNavController(navController)
        }


    }

    private fun showFragment(fragment: Fragment, tag: String) {
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

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.home -> {
                viewModel.updateFragmentStatus(R.id.home)
                //showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            R.id.history -> {
                viewModel.updateFragmentStatus(R.id.history)
                //showFragment(HistoryFragment.newInstance(), HistoryFragment.TAG)
                true
            }

            R.id.eripple_info -> {
                viewModel.updateFragmentStatus(R.id.eripple_info)
                //showFragment(ErippleInfoFragment.newInstance(), ErippleInfoFragment.TAG)
                true
            }

            R.id.my_point -> {
                viewModel.updateFragmentStatus(R.id.my_point)
                //showFragment(MyPointFragment.newInstance(), MyPointFragment.TAG)
                true
            }

            R.id.setting -> {
                viewModel.updateFragmentStatus(R.id.setting)
                //showFragment(SettingFragment.newInstance(), SettingFragment.TAG)
                true
            }

            else -> {
                false
            }

        }
    }*/


}