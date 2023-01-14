package nl.avans.drivioapp

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import nl.avans.drivioapp.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setSupportActionBar(binding.toolbar)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        binding.languageButton.setOnClickListener {
            changeLanguage()
        }
    }

    private fun changeLanguage() {
        val languages = arrayOf("English", "Dutch")

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Choose your language")
        builder.setSingleChoiceItems(languages, -1) { dialog, listItem ->
            if (listItem == 0) {
                setLocale("en")
                recreate()
            } else if (listItem == 1) {
                setLocale("nl")
                recreate()
            }
            dialog.dismiss()
        }
        val aDialog = builder.create()
        aDialog.show()
    }

    private fun setLocale(language: String) {

        // Set country language
        val locale = Locale(language)
        Locale.setDefault(locale)

        // Configuration for country language
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("Chosen_language", language)
        editor.apply()

    }

    private fun loadLocale() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("Chosen_language", "")!!
        setLocale(language)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}
