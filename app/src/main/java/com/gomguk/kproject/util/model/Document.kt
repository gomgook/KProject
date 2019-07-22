package com.gomguk.kproject.util.model

import android.os.Parcel
import android.os.Parcelable

data class Document(
    val contents: String?,
    val isbn: String?,
    val price: Int,
    val publisher: String?,
    val thumbnail: String?,
    val title: String?,
    val url: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(contents)
        dest?.writeString(isbn)
        dest?.writeInt(price)
        dest?.writeString(publisher)
        dest?.writeString(thumbnail)
        dest?.writeString(title)
        dest?.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(p: Parcel): Document {
            return Document(p)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }

}