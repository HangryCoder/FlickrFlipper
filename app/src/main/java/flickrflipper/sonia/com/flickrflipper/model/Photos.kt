package flickrflipper.sonia.com.flickrflipper.model

/**
 * Created by soniawadji on 05/05/18.
 */
data class Photos(val page: Int, val pages: Int, val perpage: Int, val total: Int, var photo: ArrayList<FlickrPhoto>)