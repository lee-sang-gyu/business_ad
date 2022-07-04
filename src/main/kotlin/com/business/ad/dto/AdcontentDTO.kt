package com.business.ad.dto

import com.business.ad.model.Adcontent

//DTO: 광고 내용
data class ReadAdcontentDTO(
    val id: Long? = null,
    val content: String,
    val image_url: String?,
    val btn_text: String?,
    val btn_url: String?
)

data class CreateAdcontentDTO(
    val id: Long,
    val content: String,
    val image_url: String?,
    val btn_text: String?,
    val btn_url: String?
) {
    fun toEnitty(): Adcontent {
        return Adcontent(
            id = id,
            content = content,
            image_url = image_url,
            btn_text = btn_text,
            btn_url = btn_url
        )
    }
}
