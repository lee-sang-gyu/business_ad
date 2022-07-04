package com.business.ad.model

import com.business.ad.dto.CreateAdcontentDTO
import com.business.ad.dto.ReadAdcontentDTO
import javax.persistence.*

//모델: 광고 내용
@Entity
data class Adcontent(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var content: String,
    var image_url: String?,
    var btn_text: String?,
    var btn_url: String?

) {
    fun toReadAdcontentDTO(): ReadAdcontentDTO {
        return ReadAdcontentDTO(
            id = id,
            content = content,
            image_url = image_url,
            btn_text = btn_text,
            btn_url = btn_url
        )
    }

    fun toCreateAdContentDTO(): CreateAdcontentDTO {
        return CreateAdcontentDTO(
            id = id,
            content = content,
            image_url = image_url,
            btn_text = btn_text,
            btn_url = btn_url
        )
    }
}