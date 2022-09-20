package noor.serry.shoestoreapp.ui.newShoeDetail

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView

object ShoeDetailPageBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["image"])
    fun image(imageView: ImageView, image: Drawable?) {
        imageView.setImageDrawable(image)
    }
}