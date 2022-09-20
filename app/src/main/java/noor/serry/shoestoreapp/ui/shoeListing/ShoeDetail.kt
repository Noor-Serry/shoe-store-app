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
        setDataInViews()
    }

    private fun setDataInViews(){
        val args : ShoeDetailArgs by navArgs()
        with(binding){
            name.text= args.name
            size.text = args.size
            companyName.text = args.companyName
            description.text = args.description
            price.text= args.price
            val imageByte = Base64.decode(args.image, Base64.DEFAULT)
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageByte,0,imageByte.size))
        }
    }


}