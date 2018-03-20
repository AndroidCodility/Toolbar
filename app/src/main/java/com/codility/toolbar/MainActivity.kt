package com.codility.toolbar

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Set Title in Toolbar
        setTitle(R.string.toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "This is Custom Toolbar Example..!!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        showSearchView(menu)
        return true
    }

    private fun showSearchView(menu: Menu) {
        //getting the search view from the menu
        val searchViewItem = menu.findItem(R.id.menuSearch)
        //getting search manager from systemService
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        //getting the search view
        val searchView = searchViewItem.actionView as SearchView
        //you can put a hint for the search input field
        searchView.queryHint = "Search Tutorials..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        //by setting it true we are making it iconified
        //so the search input will show up after taping the search iconified
        //if you want to make it visible all the time make it false
        searchView.setIconifiedByDefault(true)

        //here we will get the search query
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //do the search here
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAbout ->
                showToast("You clicked about")

            R.id.menuSettings ->
                showToast("You clicked settings")

            R.id.menuLogout ->
                showToast("You clicked logout")
        }
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}