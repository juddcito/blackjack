package lsc.dispositivosmoviles.myblackjack

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class Player(var name: String?, var goal: String?, var date: String?, var hour: String?, var cards: List<String>?, var win: Boolean?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(goal)
        parcel.writeString(date)
        parcel.writeString(hour)
        parcel.writeStringList(cards)
        parcel.writeValue(win)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }


}