package noor.serry.shoestoreapp.ui.shoeListing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.data.room.ShoesInfo

class ViewModel(application: Application) : AndroidViewModel(application) {
    private var shoesDetails : LiveData<List<ShoesInfo>>?=null

    init {
        val repository  = Repository(application)
        val email = PreferenceManager.getDefaultSharedPreferences(application).getString("email", "").toString()
        shoesDetails = repository.getAllShoesInfo(email )
    }

    fun getShoesDetails(): LiveData<List<ShoesInfo>>? {
        return shoesDetails
    }
}