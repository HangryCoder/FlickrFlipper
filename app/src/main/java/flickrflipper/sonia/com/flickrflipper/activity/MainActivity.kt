package flickrflipper.sonia.com.flickrflipper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import flickrflipper.sonia.com.flickrflipper.R
import flickrflipper.sonia.com.flickrflipper.adapter.FlickrAdapter
import flickrflipper.sonia.com.flickrflipper.model.FlickrPhoto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var flickrPhotosList: ArrayList<FlickrPhoto> = ArrayList()
    private lateinit var flickrAdapter: FlickrAdapter
    private lateinit var gridlayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flickrPhotosList.clear()

        addPhotos()

        flickrAdapter = FlickrAdapter(this, flickrPhotosList)
        gridlayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridlayoutManager
        recyclerView.adapter = flickrAdapter
    }

    private fun addPhotos() {
        var flickrPhoto = FlickrPhoto(1, "Sonia", "adcsd")
        flickrPhotosList.add(flickrPhoto)

        flickrPhoto = FlickrPhoto(1, "Shilpa", "adcsd")
        flickrPhotosList.add(flickrPhoto)

        flickrPhoto = FlickrPhoto(1, "Mommy", "adcsd")
        flickrPhotosList.add(flickrPhoto)

        flickrPhoto = FlickrPhoto(1, "Daddy", "adcsd")
        flickrPhotosList.add(flickrPhoto)

        flickrPhoto = FlickrPhoto(1, "Stephen", "adcsd")
        flickrPhotosList.add(flickrPhoto)
    }
}
