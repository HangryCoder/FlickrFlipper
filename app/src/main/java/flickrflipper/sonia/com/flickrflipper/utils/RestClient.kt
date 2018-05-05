package flickrflipper.sonia.com.flickrflipper.utils

import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.BASE_URL
import flickrflipper.sonia.com.flickrflipper.utils.Constants.Companion.DEBUG
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

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

    interface FlickrAPI {
        @GET("item/{id}.json?print=pretty")
        fun getFlickrPhotos(@Path("id") id: String): Call<ResponseBody>
    }
}