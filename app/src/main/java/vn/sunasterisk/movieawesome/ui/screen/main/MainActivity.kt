package vn.sunasterisk.movieawesome.ui.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.ui.screen.favorite.FavoriteFragment
import vn.sunasterisk.movieawesome.ui.screen.home.HomeFragment
import vn.sunasterisk.movieawesome.ui.screen.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        addFragment(HomeFragment.newInstance())
        val onNavigationSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> {
                        addFragment(HomeFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_favorite -> {
                        addFragment(FavoriteFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_setting -> {
                        addFragment(SettingFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationSelectedListener)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.frameContainer, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
