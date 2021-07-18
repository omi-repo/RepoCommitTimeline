package kost.romi.repocommittimeline.ui.searchresult

import android.graphics.*
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
import com.squareup.picasso.Transformation
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.data.Items
import kotlinx.coroutines.withContext


class SearchResultRVAdapter :
    ListAdapter<Items, SearchResultRVAdapter.SearchResultViewHolder>(SearchResultDiffCallback) {

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById(R.id.imageView)
        private val userName: TextView = itemView.findViewById(R.id.textView2)
        private val cardView: CardView = itemView.findViewById(R.id.user_CardView)
        fun bind(item: Items) {
            Picasso.get().load(item.avatar_url.toString()).transform(CircleTransform()).into(avatar)
            userName.text = item.login
            cardView.setOnClickListener {
                itemView.findNavController().navigate(
                    SearchResultDialogFragmentDirections.actionSearchResultDialogFragmentToUserRepoFragment(
                        item.repos_url.toString(), item.login, item.avatar_url.toString()
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_rv_adapter, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object SearchResultDiffCallback : DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.id == newItem.id
    }
}

class CircleTransform : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(
            squaredBitmap,
            Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        paint.setShader(shader)
        paint.setAntiAlias(true)
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}