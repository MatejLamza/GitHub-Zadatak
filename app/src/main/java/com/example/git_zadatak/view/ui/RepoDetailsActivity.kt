package com.example.git_zadatak.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.Item
import com.example.git_zadatak.utils.MyConsts
import kotlinx.android.synthetic.main.activity_repository_details.*

class RepoDetailsActivity:AppCompatActivity() {

    private lateinit var recivedProject: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        getDataFromBundle()
        initView()

        btn_repo_details_more.setOnClickListener {
            openWebBrowser(recivedProject.htmlUrl)
        }

        tv_repo_details_owner.setOnClickListener {
            val intent = Intent(this,OwnerDetailsActivity::class.java)
            intent.putExtra(MyConsts.EXTRA_PROJECT_ITEM,recivedProject)
            startActivity(intent)
        }
    }

    private fun openWebBrowser(repoURL:String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repoURL))
        startActivity(intent)
    }

    private fun getDataFromBundle(){
        if (intent.hasExtra(MyConsts.EXTRA_PROJECT_ITEM)){
            recivedProject = intent.getSerializableExtra(MyConsts.EXTRA_PROJECT_ITEM) as Item
        }
    }

    private fun initView(){
        tv_repo_details_name.text           = recivedProject.name
        tv_repo_details_language.text       = "Language: " + recivedProject.language
        tv_repo_details_date.text           = "Created: " + recivedProject.createdAt
        tv_repo_details_description.text    = recivedProject.description
        tv_repo_details_forks.text          = "Forks: " + recivedProject.forksCount.toString()
        tv_repo_details_watchers.text       = "Watchers: " + recivedProject.watchersCount.toString()
        tv_repo_details_issues.text         = "Issues: " + recivedProject.openIssuesCount.toString()
    }
}