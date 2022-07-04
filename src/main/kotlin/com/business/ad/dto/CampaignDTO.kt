package com.business.ad.dto

import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import java.util.*

//DTO: 켐페인 정보

data class ReadCampaignDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val advertiserId: String?,
    val adcontentId: String?
)
data class ViewCampaignDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?
)
data class ViewCampaignAddContentDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val adcontent: List<Adcontent>?
)

data class ReadCampaignJoinAdDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val advertiserId: String?,
    var advertiser: List<Advertiser>?,
    val adcontentId: String?,
    val adcontent: List<Adcontent>?
)

data class ReadCampaignJoinAdcDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val advertiserId: String?,
    val adcontentId: String?,
    val adcontent: List<Adcontent>?
)

data class CreateCampaignDTO(
    val id: Long,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val advertiserId: String?,
    val adcontentId: String?
) {
    fun toEnitty(): Campaign {
        return Campaign(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            advertiserId = advertiserId,
            adcontentId = adcontentId
        )
    }
}

