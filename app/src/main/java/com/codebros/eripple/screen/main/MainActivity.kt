package com.codebros.eripple.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityMainBinding
import com.codebros.eripple.screen.main.eripple_info.ErippleInfoFragment
import com.codebros.eripple.screen.main.history.HistoryFragment
import com.codebros.eripple.screen.main.home.BookMarkFragment
import com.codebros.eripple.screen.main.home.HomeFragment
import com.codebros.eripple.screen.main.home.alarm.AlarmFragment
import com.codebros.eripple.screen.main.my_point.MyPointFragment
import com.codebros.eripple.screen.main.setting.SettingFragment

class MainActivity : AppCompatActivity()/*<MainViewModel, ActivityMainBinding>(),
    NavigationBarView.OnItemSelectedListener*/ {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val home: Pair<Fragment, String> = Pair(HomeFragment.newInstance(), HOME)
    private val history: Pair<Fragment, String> = Pair(HistoryFragment(), HISTORY)
    private val erippleInfo: Pair<Fragment, String> = Pair(ErippleInfoFragment(), ERIPPLE_INFO)
    private val myPoint: Pair<Fragment, String> = Pair(MyPointFragment(), MY_POINT)
    private val setting: Pair<Fragment, String> = Pair(SettingFragment(), SETTING)

    private val bookMark: Pair<Fragment, String> = Pair(BookMarkFragment(), BOOKMARK)
    private val alarm: Pair<Fragment, String> = Pair(AlarmFragment(), ALARM)

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
        showFragment(home)
    }

    private fun initViews() {
        setBottomNavigationView()
    }


    /*private fun initViews() {
        with(binding) {
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


    }*/

    private fun setBottomNavigationView() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    showFragment(home)
                    true
                }

                R.id.history -> {
                    showFragment(history)
                    true
                }

                R.id.eripple_info -> {
                    showFragment(erippleInfo)
                    true
                }

                R.id.my_point -> {
                    showFragment(myPoint)
                    true
                }

                R.id.setting -> {
                    showFragment(setting)
                    true
                }

                else -> false
            }
        }
    }


    private fun showFragment(pair: Pair<Fragment, String>) {
        val findFragment = supportFragmentManager.findFragmentByTag(pair.second)

        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, pair.first, pair.second)
                .commitAllowingStateLoss()
        }

        //binding.titleTxv.text =
    }

    fun setNavigate(tag: String, param: Any? = null) {
        val result : Pair<Fragment, String> =
        when (tag) {
            HOME -> {
                home
            }

            HISTORY -> {
                history
            }

            ERIPPLE_INFO -> {
                val bundle = Bundle()
                bundle.putInt("selected_idx", (param ?: 0) as Int)

                erippleInfo.first.arguments = bundle
                erippleInfo
            }

            MY_POINT -> {
                myPoint
            }

            SETTING -> {
                setting
            }

            BOOKMARK -> {
                bookMark
            }

            ALARM -> {
                alarm
            }

            else -> {
                home
            }

        }

        showFragment(result)

    }

    companion object {
        const val HOME = "home"
        const val HISTORY = "history"
        const val ERIPPLE_INFO = "eripple_info"
        const val MY_POINT = "my_point"
        const val SETTING = "setting"

        const val BOOKMARK = "BOOKMARK"
        const val ALARM = "ALARM"
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