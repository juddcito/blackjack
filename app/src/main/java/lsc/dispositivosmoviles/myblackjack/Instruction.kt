package lsc.dispositivosmoviles.myblackjack

import android.os.Parcel
import android.os.Parcelable

data class Instruction(val number: String?, val instruction: String?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(number)
        parcel.writeString(instruction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Instruction> {
        override fun createFromParcel(parcel: Parcel): Instruction {
            return Instruction(parcel)
        }

        override fun newArray(size: Int): Array<Instruction?> {
            return arrayOfNulls(size)
        }
    }

}