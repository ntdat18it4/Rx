package com.example.androidadvancedday12

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvancedday12.adapter.UserAdapter
import com.example.androidadvancedday12.databinding.ActivityMainBinding
import com.example.androidadvancedday12.model.UserList
import com.example.androidadvancedday12.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchView: SearchView
    private lateinit var adapter: UserAdapter
    lateinit var userList: ArrayList<String>
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(UserList.userList)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
        viewModel.users.subscribe(
            { users ->
                adapter.userList = users
                adapter.notifyDataSetChanged()
            }, { error ->
                println(error)
            }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.onSearchQuery(it) }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


}