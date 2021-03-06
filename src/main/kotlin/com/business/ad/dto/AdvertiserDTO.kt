package com.business.ad.dto

import com.business.ad.model.Advertiser

//DTO: 광고주 정보
data class ReadAdvertiserDTO(
    val id: Long? = null,
    val name: String
)

data class CreateAdvertiserDTO(
    val id: Long,
    val name: String
) {
    fun toEnitty(): Advertiser {
        return Advertiser(
            id = id,
            name = name
        )
    }
}

data class ReadAdvertiserGetCamapignDTO(
    val id: Long? = null,
    val name: String,
    val campaign: List<ReadOnlyCampaignDTO>
)
data class ReadAdvertiserGetCamapignAndContentDTO(
    val id: Long? = null,
    val name: String,
    val campaign: List<ReadCampaignGetContentDTO>
)