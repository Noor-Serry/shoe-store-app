package noor.serry.shoestoreapp.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import noor.serry.shoestoreapp.data.room.Dao
import noor.serry.shoestoreapp.data.room.RoomDB
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.data.room.UserInfo

class Repository(var context: Context) {
      var dao : Dao
init {
    dao = RoomDB.getInstance(context)?.dao()!!
}
    fun insertNewEmail(userInfo: UserInfo){
        GlobalScope.launch(Dispatchers.IO){
            dao.insertNewEmail(userInfo)
        }
    }

    fun getPassword(email:String):String{
        return dao.getPassword(email)
    }

    fun insertNewShoes(shoesInfo: ShoesInfo){
        GlobalScope.launch(Dispatchers.IO){
            dao.insertNewShoes(shoesInfo)
        }
    }

    fun getAllShoesInfo(email:String): LiveData<List<ShoesInfo>>{
        return dao.getAllShoesInfo(email)
    }

}