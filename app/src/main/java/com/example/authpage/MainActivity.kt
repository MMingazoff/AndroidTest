package com.example.authpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.authpage.db.DbManager

class MainActivity : AppCompatActivity() {

    private lateinit var registrationLayout: LinearLayout
    private lateinit var loginLayout: LinearLayout
    private lateinit var homeLayout: LinearLayout
    private lateinit var profileLayout: LinearLayout

    val DbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registrationLayout = findViewById(R.id.registration_layout)
        loginLayout = findViewById(R.id.login_layout)
        homeLayout = findViewById(R.id.home_layout)
        profileLayout = findViewById(R.id.profile_layout)
        val registerMain = findViewById<Button>(R.id.register_main)
        val loginMain = findViewById<Button>(R.id.login_main)
        val register = findViewById<Button>(R.id.register)
        val login = findViewById<Button>(R.id.login)
        val profileUsername = findViewById<TextView>(R.id.username)
        val profileEmail = findViewById<TextView>(R.id.email)

        showHome()

        registerMain.setOnClickListener {
            showRegistration()
        }

        loginMain.setOnClickListener{
            showLogin()
        }

        register.setOnClickListener{
            val name = findViewById<EditText>(R.id.register_name).text.toString()
            val email = findViewById<EditText>(R.id.register_email).text.toString()
            val password = findViewById<EditText>(R.id.register_password).text.toString()
            if (isEmailValid(email)) {
                DbManager.openDb()
                DbManager.addUser(name, email, password)
                showHome()
            } else {
                Toast.makeText(this, "Invalid email" , Toast.LENGTH_SHORT).show()
            }
        }

        login.setOnClickListener{
            val email = findViewById<EditText>(R.id.login_email).text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()
            if (DbManager.correctPassword(email, password)){
                val data = DbManager.getUserData(email)
                profileUsername.text = data[0]
                profileEmail.text = data[1]
                showProfile()
            }
            else {
                val data = DbManager.getUserData(email)
                profileUsername.text = data[0]
                profileEmail.text = data[1]
//                "Username or password is incorrect"
                Toast.makeText(this, data[0] + data[1] + data[2] + "1" , Toast.LENGTH_SHORT).show()
                showProfile()

            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return !email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showRegistration(){
        registrationLayout.visibility=View.VISIBLE
        loginLayout.visibility=View.GONE
        homeLayout.visibility=View.GONE
        profileLayout.visibility = View.GONE
    }

    private fun showLogin(){
        registrationLayout.visibility=View.GONE
        loginLayout.visibility=View.VISIBLE
        homeLayout.visibility=View.GONE
        profileLayout.visibility = View.GONE
    }

    private fun showHome(){
        registrationLayout.visibility=View.GONE
        loginLayout.visibility=View.GONE
        homeLayout.visibility=View.VISIBLE
        profileLayout.visibility = View.GONE
    }

    private fun showProfile(){
        registrationLayout.visibility=View.GONE
        loginLayout.visibility=View.GONE
        homeLayout.visibility=View.GONE
        profileLayout.visibility = View.VISIBLE
    }
}