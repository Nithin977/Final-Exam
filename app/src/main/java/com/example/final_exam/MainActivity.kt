package com.example.final_exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddUser = findViewById<Button>(R.id.btnAddUser)
        val btnViewUsers = findViewById<Button>(R.id.btnViewUsers)

        btnAddUser.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }

        btnViewUsers.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
    }
}
