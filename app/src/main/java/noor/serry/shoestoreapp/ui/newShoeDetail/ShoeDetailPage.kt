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
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.databinding.FragmentShoeDetailPageBinding
import java.io.ByteArrayOutputStream
import java.util.*

class ShoeDetailPage : Fragment() {
      lateinit var binding : FragmentShoeDetailPageBinding
      lateinit var galleryAction : ActivityResultLauncher<String>
      lateinit var repository: Repository
      var uri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryAction = registerForActivityResult(ActivityResultContracts.GetContent(),this::addImageToView)
        binding.imageView.setOnClickListener(this::openGallery)
        repository = Repository(requireContext())
        binding.saveBt.setOnClickListener(this::saveShoesInfo)
    }

    private fun addImageToView(uri : Uri?){
        if(uri==null)return
        binding.imageView.setImageURI(uri)
        this.uri = uri
    }

    private fun openGallery(view: View){
       galleryAction.launch("image/*")
    }

    private fun saveShoesInfo(view: View){
        saveDataInRoomDB()
        goToShoeList()
    }

    private fun saveDataInRoomDB(){

         var shoesInfo = ShoesInfo()
         with(shoesInfo) {
             size = binding.size.text.toString().toInt()
             price = binding.price.text.toString().toDouble()
             email = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("email", "").toString()
             name = binding.name.text.toString()
             company = binding.companyName.text.toString()
             image = convertImageToString()
             description = binding.description.text.toString() }
         repository.insertNewShoes(shoesInfo)
    }

    private fun convertImageToString():String{
        var bm =( binding.imageView.drawable as BitmapDrawable).bitmap
        var byteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray,Base64.DEFAULT)
    }

     private fun goToShoeList(){
        findNavController().navigate(R.id.action_shoeDetailPage_to_shoeListing)
      }

    }
