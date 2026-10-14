package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onCreate")

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val registeredEmail = intent.getStringExtra("EMAIL")

        if (registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        binding.loginButton.setOnClickListener {

            val email = binding.emailEditText.text.toString().trim()

            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
        binding.registerTextView.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)

            startActivity(intent)
        }
        binding.helpTextView.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu"))

            startActivity(intent)
        }
    }
    // ── LIFECYCLE METHODS ─────────────────────────────────────────
    // Android calls each of these automatically. We only add a log
    // line so we can watch the order in Logcat.
    // The screen is now VISIBLE
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onStart")
    }
    // The screen is in FRONT and the user can interact with it
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onResume")
    }
    // The screen is LEAVING the front (e.g. Home was pressed)
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onPause")
    }
    // The screen is NO LONGER VISIBLE
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onStop")
    }
    // The user came BACK to a stopped screen
    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onRestart")
    }
    // The screen is being DESTROYED
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "LoginActivity: onDestroy")
    }
}