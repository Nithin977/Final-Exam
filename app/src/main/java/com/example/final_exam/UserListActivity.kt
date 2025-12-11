package com.example.final_exam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = UserAdapter(emptyList()) { user ->
            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("user_id", user.id)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        val userDao = AppDatabase.getInstance(this).userDao()

        lifecycleScope.launch {
            val users = withContext(Dispatchers.IO) {
                userDao.getAllUsers()
            }
            adapter.setUsers(users)
        }
    }
}
