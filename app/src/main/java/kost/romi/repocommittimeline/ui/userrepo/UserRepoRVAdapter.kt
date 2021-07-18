package kost.romi.repocommittimeline.ui.userrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.data.UserRepoResponse

class UserRepoRVAdapter :
    ListAdapter<UserRepoResponse, UserRepoRVAdapter.UserRepoViewHolder>(UserRepoDiffCallback) {

    class UserRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView = itemView.findViewById<TextView>(R.id.name_TextView)
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
