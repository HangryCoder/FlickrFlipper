package flickrflipper.sonia.com.flickrflipper.activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.ContextThemeWrapper
import flickrflipper.sonia.com.flickrflipper.R
import flickrflipper.sonia.com.flickrflipper.adapter.FlickrAdapter
import flickrflipper.sonia.com.flickrflipper.model.FlickrMainResponse
import flickrflipper.sonia.com.flickrflipper.model.FlickrPhoto
import flickrflipper.sonia.com.flickrflipper.utils.Constants
import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.GRID_COLUMNS
import flickrflipper.sonia.com.flickrflipper.utils.RestClient
import flickrflipper.sonia.com.flickrflipper.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var flickrPhotosList: ArrayList<FlickrPhoto> = ArrayList()
    private lateinit var flickrAdapter: FlickrAdapter
    private lateinit var gridlayoutManager: GridLayoutManager

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(/*ContextThemeWrapper(*/this/*,
                android.R.style.Theme_Holo_Light_Dialog)*/)
        progressDialog.setCancelable(false)
        progressDialog.setMessage(resources.getString(R.string.loading_message))
        progressDialog.show()

        fetchPhotosFromFlickr()

        flickrAdapter = FlickrAdapter(this, flickrPhotosList)
        gridlayoutManager = GridLayoutManager(this, GRID_COLUMNS)
        recyclerView.layoutManager = gridlayoutManager
        recyclerView.adapter = flickrAdapter
    }

    private fun fetchPhotosFromFlickr() {
        RestClient.getRestClient().getFlickrPhotos(resources.getString(R.string.flickr_key),
                Constants.GALLERY_ID, Constants.FLICKR_API_FORMAT, Constants.FLICKR_API_JSON_CALLBACK)
                .enqueue(object : Callback<FlickrMainResponse> {

                    override fun onResponse(call: Call<FlickrMainResponse>?, response: Response<FlickrMainResponse>?) {
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                        Utils.logd(TAG, "onResponse " + response?.body()?.photos?.photo?.get(0)?.id)
                    }

                    override fun onFailure(call: Call<FlickrMainResponse>?, t: Throwable?) {
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                        Utils.logd(TAG, "onFailure " + t?.localizedMessage)
                    }

                })
    }
}
