package my.test.movieexpert.ui.profilescreen.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.R

@AndroidEntryPoint
class ProfileScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_profile) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.bottom_nav_menu_profile)
        bottomNavigationMenu.setupWithNavController(navController)
    }
}