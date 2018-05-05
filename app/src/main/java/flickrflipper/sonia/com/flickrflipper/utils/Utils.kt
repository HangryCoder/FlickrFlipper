package flickrflipper.sonia.com.flickrflipper.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by soniawadji on 05/05/18.
 */
class Utils {

    companion object {

        fun logd(TAG: String, message: String) {
            if (Constants.DEBUG) {
                Log.d(TAG, message)
            }
        }

        fun customToast(context: Context, message: String) {
            if (Constants.DEBUG) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}