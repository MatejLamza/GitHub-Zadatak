package com.example.git_zadatak.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.git_zadatak.R
import com.example.git_zadatak.data.models.Item
import kotlinx.android.synthetic.main.item_git_repo.view.*

class GitResponseAdapter:RecyclerView.Adapter<GitResponseAdapter.GitResponseViewHolder>() {

    private var _gitRepos:ArrayList<Item> = arrayListOf()

    private lateinit var _listener:OnRepositoryClickListener

    fun loadRepos(listRepos:ArrayList<Item>){
        _gitRepos = listRepos
    }

    fun filterReposByStars(){
        _gitRepos.sortBy {
            it.stargazersCount
        }
    }

    fun filterReposByForks(){
        _gitRepos.sortBy {
            it.forksCount
        }
    }

    fun filterReposByDate(){
        _gitRepos.sortBy {
            it.updatedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitResponseViewHolder
        = GitResponseViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_git_repo,parent,false))

    override fun getItemCount(): Int = _gitRepos.size

    override fun onBindViewHolder(holder: GitResponseViewHolder, position: Int) {
       holder.gitRepo = _gitRepos[position]

        Glide
            .with(holder.itemView.context)
            .load(holder.gitRepo!!.owner.avatarUrl)
            .into(holder.itemView.iv_client_item_avatar)

        holder.itemView.cv_main_item_project.setOnClickListener {
            _listener.onRepositoryClicked(_gitRepos[position])
        }

        holder.itemView.iv_client_item_avatar.setOnClickListener {
          _listener.onOwnerAvatarClicked(_gitRepos[position])
        }
    }

    fun setOnRepositoryClickListener(listener:OnRepositoryClickListener){
        _listener = listener
    }


    class GitResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gitRepo: Item? = null
            set(value) {
                field = value

                itemView.item_project_name.text         = gitRepo!!.name
                itemView.item_project_description.text  = gitRepo?.description.toString()
                itemView.item_owner_full_name.text      = gitRepo!!.owner.login
                itemView.item_project_noForks.text      = "Forks: " + gitRepo!!.forksCount.toString()
                itemView.item_project_noIssues.text     = "Issues: " + gitRepo!!.openIssuesCount.toString()
                itemView.item_project_noWatchers.text   = "Watchers: " + gitRepo!!.watchersCount.toString()
            }
    }

    interface OnRepositoryClickListener{
        fun onRepositoryClicked(repo:Item)
        fun onOwnerAvatarClicked(repo:Item)
    }
}