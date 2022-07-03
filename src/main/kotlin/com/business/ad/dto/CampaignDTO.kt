package com.business.ad.dto

import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

//DTO: 켐페인 정보
data class ReadCampaignDTO(
    val id: Long? = null,
    val name: String,
    val start_date: Date?,
    val end_date: Date?,
    val subject_list: Int?,
    val advertiserid: String?,
    val adcontentid: String?
)

data class ReadCampaignJoinAdDTO(
    val id: Long? = null,
    val name: String,
    val start_date: Date?,
    val end_date: Date?,
    val subject_list: Int?,
    val advertiserid: String?,
    var advertiser: List<Advertiser>?,
    val adcontentid: String?,
    val adcontent: List<Adcontent>?
)

data class CreateCampaignDTO(
    val id: Long,
    val name: String,
    val start_date: Date,
    val end_date: Date,
    val subject_list: Int,
    val advertiserid: String,
    val adcontentid: String
) {
    fun toEnitty(): Campaign {
        return Campaign(
            id = id,
            name = name,
            start_date = start_date,
            end_date = end_date,
            subject_list = subject_list,
            advertiserid = advertiserid,
            adcontentid = adcontentid
        )
    }
}

