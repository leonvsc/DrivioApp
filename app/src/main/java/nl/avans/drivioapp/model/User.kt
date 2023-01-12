package nl.avans.drivioapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize // safe-args
@Entity(tableName = "user_credentials")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
): Parcelable

data class User1(
    @Json(name = "userId")
    val userId: Int?
)
