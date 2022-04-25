package uz.ilhomjon.sayyidsafo.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    val duration: String,
    @PrimaryKey
    val id: Int,
    val location: String,
    val name: String,
    val rn: Int,
    val size: String,
    val topic_id: Int
)