package com.hfad.speednet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Infos {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var typeInfo: Int? = null
        get() = field
        set(value) {
            field = value
        }

    var dateInfo: String? = null
        get() = field
        set(value) {
            field = value
        }

    var download_speedInfo: String? = null
        get() = field
        set(value) {
            field = value
        }

    var upload_speedInfo: String? = null
        get() = field
        set(value) {
            field = value
        }

    var pingInfo: String? = null
        get() = field
        set(value) {
            field = value
        }
}