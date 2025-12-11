package com.example.final_exam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val tvName = findViewById<TextView>(R.id.tvNameDetail)
        val tvEmail = findViewById<TextView>(R.id.tvEmailDetail)
        val tvAge = findViewById<TextView>(R.id.tvAgeDetail)

        val userDao = AppDatabase.getInstance(this).userDao()
        val userId = intent.getIntExtra("user_id", -1)

        lifecycleScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserById(userId)
            }

            user?.let {
                tvName.text = it.name
                tvEmail.text = it.email
                tvAge.text = it.age.toString()
            }
        }
    }
}
