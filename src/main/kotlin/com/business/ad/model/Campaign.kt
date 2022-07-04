package com.business.ad.model

import com.business.ad.dto.CreateCampaignDTO
import com.business.ad.dto.ReadCampaignDTO
import com.business.ad.dto.ViewCampaignDTO
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
    //@Column(name = "start_date")
    var startDate: Date?,
    //@Column(name = "end_date")
    var endDate: Date?,
    //@Column(name = "subject_list")
    var subjectList: Int?,
    //@Column(name = "advertiser_id")
    var advertiserId: String?,
    //@Column(name = "adcontent_id")
    var adcontentId: String?
) {
    fun toViewCampaignDTO(): ViewCampaignDTO {
        return ViewCampaignDTO(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList
        )
    }

    fun toReadCampaignDTO(): ReadCampaignDTO {
        return ReadCampaignDTO(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            advertiserId = advertiserId,
            adcontentId = adcontentId
        )
    }

    fun toCreateCampaignDTO(): CreateCampaignDTO {
        return CreateCampaignDTO(
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