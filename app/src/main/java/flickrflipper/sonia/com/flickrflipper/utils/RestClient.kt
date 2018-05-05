package flickrflipper.sonia.com.flickrflipper.utils

import flickrflipper.sonia.com.flickrflipper.model.FlickrMainResponse
import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.BASE_URL
import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by soniawadji on 05/05/18.
 */
class RestClient {

    companion object {

        private val level = if (DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE


        fun getRetrofitBuilder(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(level))
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun getRestClient(): FlickrAPI {
            return getRetrofitBuilder().create<FlickrAPI>(FlickrAPI::class.java)
        }

    }

    /**
     * https://api.flickr.com/services/rest/?method=flickr.galleries.getPhotos
     * &api_key=APIKEY&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1
     * */

    interface FlickrAPI {
        @GET("?method=flickr.galleries.getPhotos")
        fun getFlickrPhotos(@Query("api_key") apiKey: String,
                            @Query("gallery_id") galleryId: String,
                            @Query("format") format: String,
                            @Query("nojsoncallback") noJsonCallback: Int): Call<FlickrMainResponse>
    }
}