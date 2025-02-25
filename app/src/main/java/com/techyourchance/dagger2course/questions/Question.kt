package com.techyourchance.dagger2course.questions

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Question(
        @SerializedName("title") val title: String,
        @SerializedName("question_id") val id: String
) : Parcelable {
        constructor(parcel: Parcel) : this(
                title = parcel.readString() ?: "",
                id = parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(id)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Question> {
                override fun createFromParcel(parcel: Parcel): Question {
                        return Question(parcel)
                }

                override fun newArray(size: Int): Array<Question?> {
                        return arrayOfNulls(size)
                }
        }
}