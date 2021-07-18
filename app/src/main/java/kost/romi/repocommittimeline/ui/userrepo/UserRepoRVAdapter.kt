package kost.romi.repocommittimeline.ui.userrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.data.UserRepoResponse

class UserRepoRVAdapter :
    ListAdapter<UserRepoResponse, UserRepoRVAdapter.UserRepoViewHolder>(UserRepoDiffCallback) {

    class UserRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatarTextView =
            itemView.findViewById<ImageView>(R.id.user_avatar_ImageView)
        private val nameTextView = itemView.findViewById<TextView>(R.id.user_name_TextView)
        private val repoNameTextView = itemView.findViewById<TextView>(R.id.repo_name_TextView)
        private val privateTextView = itemView.findViewById<TextView>(R.id.private_TextView)
        private val repoDescriptionTextView =
            itemView.findViewById<TextView>(R.id.repo_description_TextView)
        private val createdAtTextView = itemView.findViewById<TextView>(R.id.created_at_TextView)
        private val updatedAtTextView = itemView.findViewById<TextView>(R.id.updated_at_TextView)
        private val stargazersTextView = itemView.findViewById<TextView>(R.id.stargazers_TextView)
        private val languageImageView = itemView.findViewById<ImageView>(R.id.language_ImageView)
        private val languageTextView = itemView.findViewById<TextView>(R.id.language_TextView)
        private val archivedTextView = itemView.findViewById<TextView>(R.id.archived_TextView)
        private val forkTextView = itemView.findViewById<TextView>(R.id.fork_TextView)
        private val hasWikiTextView = itemView.findViewById<TextView>(R.id.has_wiki_TextView)
        private val openIssuesTextView = itemView.findViewById<TextView>(R.id.open_issues_TextView)
        private val defaultBranchTextView =
            itemView.findViewById<TextView>(R.id.default_branch_TextView)
        private val licenseTextView = itemView.findViewById<TextView>(R.id.license_TextView)
        fun bind(item: UserRepoResponse?) {
            nameTextView.text = item?.name ?: "null"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_repo_rv_adapter, parent, false)
        return UserRepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object UserRepoDiffCallback : DiffUtil.ItemCallback<UserRepoResponse>() {
    override fun areItemsTheSame(oldItem: UserRepoResponse, newItem: UserRepoResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserRepoResponse, newItem: UserRepoResponse): Boolean {
        return oldItem.id == newItem.id
    }
}
