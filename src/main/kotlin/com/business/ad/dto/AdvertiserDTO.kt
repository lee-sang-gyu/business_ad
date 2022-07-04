package com.business.ad.dto

import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import java.util.*

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

data class ReadAdvertiserJoinCamapignDTO(
    val id: Long? = null,
    val name: String,
    val campaign: List<ViewCampaignDTO>
)
data class ReadAdvertiserJoinCamapignDetailDTO(
    val id: Long? = null,
    val name: String,
    val campaign: List<ViewCampaignAddContentDTO>
)