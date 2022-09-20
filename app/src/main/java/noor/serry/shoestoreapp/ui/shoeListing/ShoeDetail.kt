package noor.serry.shoestoreapp.ui.shoeListing

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.databinding.FragmentShoeDetailBinding

class ShoeDetail : Fragment() {

lateinit var binding :FragmentShoeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : ShoeDetailArgs by navArgs()

        with(args) {
            binding.name.setText(name)
            binding.size.setText(size)
            binding.companyName.setText(companyName)
            binding.description.setText(description)
            binding.price.setText(price)
            val imageByte = Base64.decode(image, Base64.DEFAULT)
            binding.imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageByte,0,imageByte.size))
        }
    }


}