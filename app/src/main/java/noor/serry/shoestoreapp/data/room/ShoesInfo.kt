package noor.serry.shoestoreapp.data.room

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoesInfo(@Nullable var image : String="", var price : Double = 0.0
                     , var size : Int = 0, var email:String= "", var name : String = "", var company : String = "",
                     var description : String = ""){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

