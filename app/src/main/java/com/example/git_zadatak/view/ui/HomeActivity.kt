package com.example.git_zadatak.view.ui

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.Item
import com.example.git_zadatak.utils.MyConsts
import com.example.git_zadatak.utils.MyUtils
import com.example.git_zadatak.view.adapters.GitResponseAdapter
import com.example.git_zadatak.viewmodels.HomeViewModel
import com.example.git_zadatak.viewmodels.factories.HomeVMFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*
import javax.inject.Inject

class HomeActivity : ScopedActivity(), GitResponseAdapter.OnRepositoryClickListener,
    AdapterView.OnItemSelectedListener {


    @Inject
    lateinit var factory: HomeVMFactory

    private var repoList: ArrayList<Item> = arrayListOf()

    private lateinit var homeVM: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: GitResponseAdapter

    private var userLoggedInFlag:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromBundle()

        initRecyclerView()
        initSpinner()

        ddl_sort.onItemSelectedListener = this

        homeVM = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        sv_repositories.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                homeVM.getNewRepos(sv_repositories.query.toString())
                MyUtils.hideKeyboard(this@HomeActivity)
                return true
            }
        })

        if (!userLoggedInFlag){
            iv_info.visibility = View.GONE
        }

        iv_info.setOnClickListener {
            val intent = Intent(this,UserProfileActivity::class.java)
            startActivity(intent)
        }

        homeVM.liveRepoOwner.observe(this, Observer {
            GlobalScope.launch {
                val jobCache = launch { homeVM.cacheRepoOwner(it) }
                jobCache.join()
                if (jobCache.isCompleted) {
                    homeVM.redirectUserToNewActivity(this@HomeActivity)
                }
            }
        })

        homeVM.liveRepos.observe(this, Observer {
            repoList = it.items as ArrayList<Item>
            adapter.loadRepos(repoList)
            adapter.notifyDataSetChanged()
        })

        homeVM.spinner.observe(this, Observer { value ->
            value.let { show ->
                load_spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        when (pos) {
            0 -> {
                adapter.filterReposByStars()
                adapter.notifyDataSetChanged()
            }
            1 -> {
                adapter.filterReposByForks()
                adapter.notifyDataSetChanged()
            }
            2 -> {
                adapter.filterReposByDate()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onRepositoryClicked(repo: Item) {
        val intent = Intent(baseContext, RepoDetailsActivity::class.java)
        intent.putExtra(MyConsts.EXTRA_PROJECT_ITEM, repo)
        startActivity(intent)
    }

    override fun onOwnerAvatarClicked(repo: Item) {
        homeVM.getRepoOwnerDetails(repo.owner.login)
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.drop_down_sort,
            R.layout.item_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            ddl_sort.adapter = adapter
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rec_projects)
        recyclerView.setHasFixedSize(true)

        manager = LinearLayoutManager(this)
        adapter = GitResponseAdapter()
        adapter.setOnRepositoryClickListener(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun getDataFromBundle(){
        if (intent.hasExtra(MyConsts.EXTRA_UI_FLAG)){
            userLoggedInFlag = intent.getSerializableExtra(MyConsts.EXTRA_UI_FLAG) as Boolean
        }
    }
}