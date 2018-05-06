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
import kotlinx.android.synthetic.main.flickr_photo_front_layout.view.*
import kotlinx.android.synthetic.main.flickr_photo_back_layout.view.*

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

        //Need to create this url inorder to access the photos from the gallery
        val photoUrl = "https://farm${flickrPhoto.farm}.staticflickr.com/" +
                "${flickrPhoto.server}/${flickrPhoto.id}_${flickrPhoto.secret}.jpg"

        Glide.with(context).load(photoUrl).into(holder?.itemView?.flicktPhotoFrontLayout?.flickrPhoto!!)

        holder.itemView?.flickrPhotoTitleTV?.text = flickrPhoto.title
    }

    override fun getItemCount() = flickrPhotosList.size

    fun setFlickrPhotoList(flickrPhotosList: ArrayList<FlickrPhoto>) {
        this.flickrPhotosList.clear()
        this.flickrPhotosList = flickrPhotosList
    }

    inner class FlickrHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.flickrMainLayout.setOnClickListener {
                itemView.flickrMainLayout.flipTheView()
            }
        }
    }
}
