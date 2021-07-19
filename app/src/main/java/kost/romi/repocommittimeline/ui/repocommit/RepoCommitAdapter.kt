package kost.romi.repocommittimeline.ui.repocommit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.data.RepoCommitResponse

class RepoCommitAdapter :
    ListAdapter<RepoCommitResponse, RepoCommitAdapter.RepoCommitViewHolder>(RepoCommitDiffCallback) {

    class RepoCommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val shaTextView = itemView.findViewById<TextView>(R.id.commit_date_TextView)
        fun bind(currentItem: RepoCommitResponse?, position: Int) {
            shaTextView.text = "${position}"
        }

        fun bind(
            previousItem: RepoCommitResponse?,
            currentItem: RepoCommitResponse?,
            position: Int
        ) {
            shaTextView.text = "${position}"
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoCommitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_commit_rv_adapter, parent, false)
        return RepoCommitAdapter.RepoCommitViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoCommitAdapter.RepoCommitViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(getItem(position), position)

        } else {
            holder.bind(getItem(position - 1), getItem(position), position)
        }
    }

}

object RepoCommitDiffCallback : DiffUtil.ItemCallback<RepoCommitResponse>() {
    override fun areItemsTheSame(
        oldItem: RepoCommitResponse,
        newItem: RepoCommitResponse
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RepoCommitResponse,
        newItem: RepoCommitResponse
    ): Boolean {
        return oldItem.node_id == newItem.node_id
    }
}