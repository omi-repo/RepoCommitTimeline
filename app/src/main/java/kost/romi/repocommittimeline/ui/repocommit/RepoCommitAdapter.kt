package kost.romi.repocommittimeline.ui.repocommit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.data.RepoCommitResponse

class RepoCommitAdapter(private val dataSetSize: Int) :
    ListAdapter<RepoCommitResponse, RepoCommitAdapter.RepoCommitViewHolder>(RepoCommitDiffCallback) {

    private val TAG = "appDebugAdapter"

    class RepoCommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timelineLineView1 = itemView.findViewById<View>(R.id.timeline_line_View1)
        private val timelineLineView2 = itemView.findViewById<View>(R.id.timeline_line_View2)
        private val positionTextView = itemView.findViewById<TextView>(R.id.position_TextView)
        private val commitDateTextView = itemView.findViewById<TextView>(R.id.commit_date_TextView)
        private val timelineIconImageView =
            itemView.findViewById<ImageView>(R.id.timeline_icon_ImageView)
        private val commitMaterialCardView =
            itemView.findViewById<MaterialCardView>(R.id.commit_MaterialCardView)
        private val commitContentCostraintLayout =
            itemView.findViewById<ConstraintLayout>(R.id.commit_content_CostraintLayout)
        private val timelineConstraintLayout =
            itemView.findViewById<ConstraintLayout>(R.id.timeline_ConstraintLayout)

        fun bind(currentItem: RepoCommitResponse?, position: Int, dataSetSize: Int) {
            timelineLineView1.visibility = View.INVISIBLE
            positionTextView.text = "${position}"
            commitDateTextView.text =
                "${(currentItem?.commit?.committer?.date).toString().substring(0, 19)}"
            if (position == dataSetSize - 1) {
                timelineLineView2.visibility = View.INVISIBLE
            }

        }

        fun bind(
            previousItem: RepoCommitResponse?,
            currentItem: RepoCommitResponse?,
            position: Int,
            dataSetSize: Int
        ) {
            // current item position
            positionTextView.text = "${position}"

            // Date
            commitDateTextView.text =
                "${(currentItem?.commit?.committer?.date).toString().substring(0, 19)}"

            // if previous Item date is tha same as current Item, then current item timeline_icon_ImageView is gone.
            if (
                (previousItem?.commit?.committer?.date).toString().substring(0, 11) ==
                (currentItem?.commit?.committer?.date).toString().substring(0, 11)
            ) {
                timelineIconImageView.visibility = View.GONE
            } else {
                commitContentCostraintLayout.setPadding(0, 20, 0, 0)
                timelineLineView1.layoutParams.height = 50

            }

            // If last item, timelineLineView2 is View.INVISIBLE
            if (position == dataSetSize - 1) {
                timelineLineView2.visibility = View.INVISIBLE
            }
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
        Log.i(TAG, "onBindViewHolder: dataSetSize = ${dataSetSize}")
        if (position == 0) {
            holder.bind(getItem(position), position, dataSetSize)
        } else {
            holder.bind(getItem(position - 1), getItem(position), position, dataSetSize)
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