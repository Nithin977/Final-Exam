package com.example.final_exam

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val userDao = AppDatabase.getInstance(this).userDao()

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val age = etAge.text.toString().trim().toInt()

            val user = User(name = name, email = email, age = age)

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    userDao.insertUser(user)
                }
                finish()
            }
        }
    }
}
