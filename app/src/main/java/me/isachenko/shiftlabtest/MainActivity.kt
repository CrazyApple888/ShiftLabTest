package me.isachenko.shiftlabtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import me.isachenko.shiftlabtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


//        val sharedPreferences =
//            getSharedPreferences(getString(R.string.preferences_key), MODE_PRIVATE)
//
//        if (sharedPreferences.contains(getString(R.string.saved_name_key)) && sharedPreferences.contains(
//                getString(R.string.saved_surname_key))
//        ) {
//            Log.i("MYTAG", "CHANGE START")
//            //navController.graph.startDestination = R.id.greetingsFragment
//            val navOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.greetingsFragment, true)
//                .build()
//            navController.navigate(R.id.action_registrationFragment_to_greetingsFragment,null, navOptions)
//        }

    }
}