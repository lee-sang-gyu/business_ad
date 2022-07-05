package com.business.ad.model

import com.business.ad.dto.*
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

//모델: 캠페인 정보
@Entity
data class Campaign(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var startDate: Date?,
    var endDate: Date?,
    var subjectList: Int?,

    @ManyToMany
    var advertiserList: MutableList<Advertiser>?,
    @ManyToMany
    var adcontentList: MutableList<Adcontent>?
) {
    fun toReadCampaignDTO(): ReadCampaignDTO {
        return ReadCampaignDTO(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            adcontentList = adcontentList,
            advertiserList = advertiserList
        )
    }

    fun toCreateCampaignDTO(): CreateCampaignDTO {
        return CreateCampaignDTO(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            advertiserList = advertiserList,
            adcontentList = adcontentList
        )
    }
    fun toOnlyCampaignDTO(): ReadOnlyCampaignDTO {
       return ReadOnlyCampaignDTO(
           id = id,
           name = name,
           startDate = startDate,
           endDate = endDate,
           subjectList = subjectList
       )
   }
    fun toCampaignGetContentDTO(): ReadCampaignGetContentDTO {
        return ReadCampaignGetContentDTO(
            id = id,
            name = name,
            startDate = startDate,
            endDate = endDate,
            subjectList = subjectList,
            adcontentList = adcontentList
        )
    }

}