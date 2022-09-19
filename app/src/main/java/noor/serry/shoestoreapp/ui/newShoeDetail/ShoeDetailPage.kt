package noor.serry.shoestoreapp.ui.newShoeDetail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.databinding.FragmentShoeDetailPageBinding
import noor.serry.shoestoreapp.ui.newShoeDetail.ShoeDetailPage.ShoeDetailPage.binding
import java.io.ByteArrayOutputStream


class ShoeDetailPage : Fragment() {

      lateinit var galleryAction : ActivityResultLauncher<String>
      var uri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeDetailPageBinding.inflate(inflater,container,false)
        val shoeInfoViewModel = ViewModelProvider(requireActivity()).get(ShoeInfoViewModel::class.java)
        binding.viewModel = shoeInfoViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryAction = registerForActivityResult(ActivityResultContracts.GetContent(),this::addImageSelectedToView)
        binding.imageView.setOnClickListener(this::openGallery)

    }

    private fun addImageSelectedToView(uri : Uri?){
        if(uri==null)return
        binding.imageView.setImageURI(uri)
        this.uri = uri
    }

    private fun openGallery(view: View){
       galleryAction.launch("image/*")
    }

 object ShoeDetailPage{
     lateinit var binding : FragmentShoeDetailPageBinding
    fun convertImageToString():String{
        val bm =( binding.imageView.drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray,Base64.DEFAULT)
    }
 }

    }
