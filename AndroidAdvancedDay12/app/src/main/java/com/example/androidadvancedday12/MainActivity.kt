package com.example.androidadvancedday12

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvancedday12.adapter.UserAdapter
import com.example.androidadvancedday12.databinding.ActivityMainBinding
import com.example.androidadvancedday12.model.User
import com.example.androidadvancedday12.model.UserList
import com.example.androidadvancedday12.viewmodel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.Boolean
import kotlin.String

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchView : SearchView
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

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search)
        search(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun search(searchView: SearchView) {

        io.reactivex.rxjava3.core.Observable.create<String>{ emmiter ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(userList.contains(query)){
                        adapter.
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!emmiter.isDisposed){
                        emmiter.onNext(newText)
                    }
                    return false
                }

            })
        }
            .debounce(1000, TimeUnit.MICROSECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("AAA", "Search: $it")
                viewModel.searchUser(it)
            })

    }

}