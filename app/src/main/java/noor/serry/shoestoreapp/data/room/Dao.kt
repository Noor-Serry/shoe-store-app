package noor.serry.shoestoreapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewEmail(userInfo: UserInfo)
    @Query("Select  password from UserInfo where email = :email")
    fun getPassword(email:String):String
   @Insert
    fun insertNewShoes (shoesInfo: ShoesInfo)
    @Query("Select * from ShoesInfo where email = :email")
    fun getAllShoesInfo(email:String): LiveData<List<ShoesInfo>>


}