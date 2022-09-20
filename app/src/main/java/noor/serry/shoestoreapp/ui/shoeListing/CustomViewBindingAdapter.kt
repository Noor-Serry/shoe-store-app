package noor.serry.shoestoreapp.ui.shoeListing

import androidx.databinding.BindingAdapter
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView

object CustomViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageBitmap")
    fun setImageBitmap(view: ImageView, newValue: String?) {
        val imageByte = Base64.decode(newValue, Base64.DEFAULT)
        view.setImageBitmap(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size))
    }
}