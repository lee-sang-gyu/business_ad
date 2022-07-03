package com.business.ad.model

import com.business.ad.dto.CreateCampaignDTO
import com.business.ad.dto.ReadCampaignDTO
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

//모델: 캠페인 정보
@Entity
data class Campaign(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var start_date: Date,
    var end_date: Date,
    var subject_list: Int,
    var advertiser_id: String,
    var adcontent_id: String
) {
    fun toReadCampaignDTO(): ReadCampaignDTO {
        return ReadCampaignDTO(
            id = id,
            name = name,
            start_date = start_date,
            end_date = end_date,
            subject_list = subject_list,
            advertiser_id = advertiser_id,
            adcontent_id = adcontent_id
        )
    }

    fun toCreateCampaignDTO(): CreateCampaignDTO {
        return CreateCampaignDTO(
            id = id,
            name = name,
            start_date = start_date,
            end_date = end_date,
            subject_list = subject_list,
            advertiser_id = advertiser_id,
            adcontent_id = adcontent_id
        )
    }
}