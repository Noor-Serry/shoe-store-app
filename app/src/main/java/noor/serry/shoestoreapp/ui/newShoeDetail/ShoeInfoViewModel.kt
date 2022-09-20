package noor.serry.shoestoreapp.ui.newShoeDetail


import android.app.Application
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.ui.newShoeDetail.ShoeDetailPage.ShoeDetailPage.binding
import noor.serry.shoestoreapp.ui.newShoeDetail.ShoeDetailPage.ShoeDetailPage.convertImageToString



class ShoeInfoViewModel ( application: Application) : AndroidViewModel(application)  {
    private val repository  = Repository(application)
    var size= MutableLiveData<String>()
    var price= MutableLiveData<String>()
    var name= MutableLiveData<String>()
    var company= MutableLiveData<String>()
    var description= MutableLiveData<String>()
    var image = MutableLiveData<Drawable>()
    init {
        image.value = getDrawable(application,R.drawable.ic_baseline_image_24)
    }
    fun setOnClickListener(){
        saveDataInDB()
        removeDataFromMutableLiveData()
        goToShoeList()
    }

    fun saveDataInDB(){
        val email = PreferenceManager.getDefaultSharedPreferences(getApplication()).getString("email", "").toString()
        val image = convertImageToString()
        val shoesInfo =ShoesInfo(image, price.value!!.toDouble(),size.value!!.toInt(),email, name.value!!,company.value!!,description.value!!)
        repository.insertNewShoes(shoesInfo)

    }

    private fun removeDataFromMutableLiveData(){
        size.value = "" ; price.value = "" ; name.value = ""
        company.value = "" ; description.value = ""
    }

     fun goToShoeList(){
        Navigation.findNavController(binding.root).navigate(R.id.action_shoeDetailPage_to_shoeListing)
    }






}