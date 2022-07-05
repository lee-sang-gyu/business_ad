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
    val advertiserList: List<Advertiser>?,
    val adcontentList: List<Adcontent>?
)

data class ReadOnlyCampaignDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?
)
data class ReadCampaignGetContentDTO(
    val id: Long? = null,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val adcontentList: List<Adcontent>?
)
data class CreateCampaignDTO(
    val id: Long,
    val name: String,
    val startDate: Date?,
    val endDate: Date?,
    val subjectList: Int?,
    val advertiserList: MutableList<Advertiser>?,
    val adcontentList: MutableList<Adcontent>?
) {
    fun toEnitty(): Campaign {
        return Campaign(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            advertiserList = advertiserList,
            adcontentList = adcontentList
        )
    }
}

