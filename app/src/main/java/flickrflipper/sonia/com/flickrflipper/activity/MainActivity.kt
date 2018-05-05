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
import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.GRID_SPACING
import flickrflipper.sonia.com.flickrflipper.utils.EqualSpacingItemDecoration
import flickrflipper.sonia.com.flickrflipper.utils.EqualSpacingItemDecoration.Companion.GRID
import flickrflipper.sonia.com.flickrflipper.utils.RestClient
import flickrflipper.sonia.com.flickrflipper.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

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
        recyclerView.addItemDecoration(EqualSpacingItemDecoration(GRID_SPACING, GRID))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = flickrAdapter
    }

    private fun fetchPhotosFromFlickr() {
        RestClient.getRestClient().getFlickrPhotos(resources.getString(R.string.flickr_key),
                Constants.GALLERY_ID, Constants.FLICKR_API_FORMAT, Constants.FLICKR_API_JSON_CALLBACK)
                .enqueue(object : Callback<FlickrMainResponse> {

                    override fun onResponse(call: Call<FlickrMainResponse>?, response: Response<FlickrMainResponse>?) {
                        dismissProgressDialog()
                        flickrPhotosList = response?.body()?.photos?.photo!!

                        flickrAdapter.setFlickrPhotoList(flickrPhotosList)
                        flickrAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<FlickrMainResponse>?, t: Throwable?) {
                        dismissProgressDialog()
                        when (t) {
                            is SocketTimeoutException -> Utils.customToast(applicationContext, resources.getString(R.string.something_went_wrong))
                            is IOException -> Utils.customToast(applicationContext, resources.getString(R.string.no_internet_connection))
                            else -> Utils.customToast(applicationContext, resources.getString(R.string.something_went_wrong))
                        }
                        Utils.logd(TAG, "onFailure " + t?.localizedMessage)
                    }

                })
    }

    private fun dismissProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
