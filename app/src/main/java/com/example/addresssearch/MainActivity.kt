package com.example.addresssearch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.mauker.materialsearchview.MaterialSearchView
import com.example.addresssearch.models.Address
import com.example.addresssearch.repo.AddressRepo
import com.example.addresssearch.viewmodels.AddressViewModel
import com.example.addresssearch.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val dataList = ArrayList<Address>()
    private val suggestions = ArrayList<String>()
    private val repo = AddressRepo()
    private lateinit var viewModel: AddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val vmFactory = ViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, vmFactory)[AddressViewModel::class.java]
        getSearchReady()

    }

    private fun getSearchReady() {
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                CoroutineScope(IO).launch {
                    withContext(CoroutineScope(IO).coroutineContext) {
                        dataList.clear()
                        val xtr = viewModel.getSearchResults(newText).data.addressList
                        dataList.addAll(xtr)
                        Log.d("qwaszx", "onSearchViewOpened:${dataList.size} ")
                        suggestions.clear()
                        for (i in 0 until dataList.size) {
                            suggestions.add(dataList[i].addressString)
                            Log.d("qwaszx", "onSearchViewOpened:${dataList[i].addressString}")
                        }
                        search_view.addSuggestions(suggestions)
                    }
                }
                return true
            }
        })

        search_view.setSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewOpened() {
                CoroutineScope(IO).launch {
                    withContext(CoroutineScope(IO).coroutineContext) {
                        dataList.addAll(viewModel.getSearchResults("").data.addressList)
                        for (i in 0 until dataList.size) {
                            suggestions.add(dataList[i].addressString)
                            Log.d("qwaszx", "onSearchViewOpened:${dataList[i].addressString}")
                        }
                        search_view.addSuggestions(suggestions)
                    }
                }
            }

            override fun onSearchViewClosed() {

            }
        })
        search_view.setOnItemClickListener { _, _, position, _ -> // Do something when the suggestion list is clicked.
            val suggestion = search_view.getSuggestionAtPosition(position)
            search_view.setQuery(suggestion, false)
        }
        search_view.setOnClearClickListener {

        }
        bt_clearHistory.setOnClickListener {
            search_view.clearHistory()
        }
        bt_clearSuggestions.setOnClickListener {
            search_view.clearSuggestions()
        }
        bt_clearAll.setOnClickListener {
            search_view.clearAll()
        }

        search_view.adjustTintAlpha(0.8f)
        search_view.setOnItemLongClickListener { _, _, i, _ ->
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                search_view.openSearch()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (search_view.isOpen)
            search_view.closeSearch()
        else
            super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        search_view.clearSuggestions()
    }

    override fun onResume() {
        super.onResume()
        search_view.activityResumed()
    }

}