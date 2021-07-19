package kost.romi.repocommittimeline.ui.userrepo

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.colorMap
import kost.romi.repocommittimeline.data.UserRepoResponse
import kost.romi.repocommittimeline.ui.searchresult.CircleTransform


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
        private val stargazersImageView =
            itemView.findViewById<ImageView>(R.id.stargazers_ImageView)
        private val stargazersTextView = itemView.findViewById<TextView>(R.id.stargazers_TextView)
        private val languageImageView = itemView.findViewById<ImageView>(R.id.language_ImageView)
        private val languageTextView = itemView.findViewById<TextView>(R.id.language_TextView)
        private val forkImageView =
            itemView.findViewById<ImageView>(R.id.fork_ImageView)
        private val forkTextView = itemView.findViewById<TextView>(R.id.fork_TextView)
        private val openIssuesTextView = itemView.findViewById<TextView>(R.id.open_issues_TextView)
        private val defaultBranchImageView =
            itemView.findViewById<ImageView>(R.id.default_branch_ImageView)
        private val defaultBranchTextView =
            itemView.findViewById<TextView>(R.id.default_branch_TextView)
        private val licenseImageView = itemView.findViewById<ImageView>(R.id.license_ImageView)
        private val licenseTextView = itemView.findViewById<TextView>(R.id.license_TextView)
        private val userRepoCardView = itemView.findViewById<CardView>(R.id.user_repo_CardView)

        fun bind(item: UserRepoResponse?) {

            userRepoCardView.setOnClickListener {
                itemView.findNavController().navigate(
                    UserRepoFragmentDirections.actionUserRepoFragmentToRepoCommitFragment(
                        item?.commits_url.toString(),
                        item?.owner?.login.toString(),
                        item?.owner?.avatar_url.toString()
                    )
                )
            }

            // user avatar
            Picasso.get().load(item?.owner?.avatar_url.toString()).transform(CircleTransform())
                .into(userAvatarTextView)
            // user name
            if (item?.name == null) {
                nameTextView.visibility = View.INVISIBLE
            } else {
                nameTextView.text = item?.owner?.login
            }
            // repo name
            repoNameTextView.text = item?.full_name
            // private repo or not
            if (item?.private == true) {
                privateTextView.text = "(private)"
            } else {
                privateTextView.text = "(public)"
            }
            // repo description
            repoDescriptionTextView.text = item?.description
            // created at
            createdAtTextView.text =
                "Created at: ${item?.created_at.toString().substringBeforeLast("GMT")}"
            // updated at
            updatedAtTextView.text =
                "Updated at: ${item?.updated_at.toString().substringBeforeLast("GMT")}"
            // star
            stargazersTextView.text = "Stars: ${item?.stargazers_count}"
            // language ImageView
            val biggerCircle = ShapeDrawable(OvalShape())
            biggerCircle.intrinsicHeight = 18
            biggerCircle.intrinsicWidth = 18
            biggerCircle.bounds = Rect(18, 18, 18, 18)
            try {
                biggerCircle.paint.color =
                    Color.parseColor(colorMap.get(item?.language)) //you can give any color here
            } catch (e: NullPointerException) {
                Log.e("UserRepoAdapter", e.printStackTrace().toString())
            }
            languageImageView.setImageDrawable(biggerCircle)
//            languageImageView.setColorFilter(colorMap.get(item?.language))
            // language TextView
            languageTextView.text = item?.language
            // fork
            forkTextView.text = "Forks: ${item?.forks_count}"
            // wiki
            openIssuesTextView.text = "Issues: ${item?.open_issues_count.toString()}"
            // default branch
            defaultBranchTextView.text = "Default branch: ${item?.default_branch.toString()}"
            // license
            if (item?.license?.name.toString().isEmpty()) {
                licenseImageView.visibility = View.GONE
                licenseTextView.visibility = View.GONE
            } else {
                Log.i("license: ", "bind: ${item?.license?.name}")
                licenseTextView.setText(item?.license?.name.toString())
            }
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
