package noor.serry.shoestoreapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(@PrimaryKey (autoGenerate = false)var email : String="", var password : String="")
