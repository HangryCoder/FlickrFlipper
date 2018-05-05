package flickrflipper.sonia.com.flickrflipper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import flickrflipper.sonia.com.flickrflipper.R
import flickrflipper.sonia.com.flickrflipper.model.FlickrPhoto
import kotlinx.android.synthetic.main.flickr_photo_item_layout.view.*

/**
 * Created by soniawadji on 05/05/18.
 */

class FlickrAdapter(private val context: Context, private var flickrPhotosList: ArrayList<FlickrPhoto>)
    : RecyclerView.Adapter<FlickrAdapter.FlickrHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FlickrHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.flickr_photo_item_layout, parent, false)
        return FlickrHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrHolder?, position: Int) {
        val flickrPhoto = flickrPhotosList[position]

        Glide.with(context).load(R.mipmap.ic_launcher).into(holder?.itemView?.flickrPhoto!!)
    }

    override fun getItemCount() = flickrPhotosList.size

    inner class FlickrHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
