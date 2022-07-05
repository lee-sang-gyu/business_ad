package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.error.NotFoundException
import com.business.ad.model.Campaign
import com.business.ad.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable

//서비스: 캠페인 정보
@Component
class CampaignService {
    @Autowired
    lateinit var campaignRepository: CampaignRepository

    //전체 조회
    fun getCampaign(): List<ReadCampaignDTO> {
        val campaign = campaignRepository.findAll()
        return campaign.map { it.toReadCampaignDTO() }
    }

    //id로 조회
    fun getCampaignById(
        @PathVariable(value = "id") campaignId: Long
    ): ReadCampaignDTO {
        val campaign = findCampaignByIdOrThrowNotFound(campaignId)
        return campaign.toReadCampaignDTO()
    }

    //전체 조회: 광고주, 광고내용 제외
    fun getOnlyCampaign(): List<ReadOnlyCampaignDTO> {
        val campaign = campaignRepository.findAll()

        return campaign.map { it.toOnlyCampaignDTO() }
    }

    //캠페인 생성
    fun createCampaign(
        createCampaignDTO: CreateCampaignDTO
    ): CreateCampaignDTO {

        val compaign = campaignRepository.save(createCampaignDTO.toEnitty())
        return compaign.toCreateCampaignDTO()
    }

    //id로 변경
    fun updateCampaignById(
        @PathVariable(value = "id") campaignId: Long,
        createCampaignDTO: CreateCampaignDTO
    ): ReadCampaignDTO {
        val existingCampaign = findCampaignByIdOrThrowNotFound(campaignId)

        var updatedCampaign = existingCampaign.copy(
            name = if (createCampaignDTO.name == null) {
                existingCampaign.name
            } else {
                createCampaignDTO.name
            },
            startDate = if (createCampaignDTO.startDate == null) {
                existingCampaign.startDate
            } else {
                createCampaignDTO.startDate
            },
            endDate = if (createCampaignDTO.endDate == null) {
                existingCampaign.endDate
            } else {
                createCampaignDTO.endDate
            },
            subjectList = if (createCampaignDTO.subjectList == null) {
                existingCampaign.subjectList
            } else {
                createCampaignDTO.subjectList
            },
            advertiserList = if (createCampaignDTO.advertiserList == null) {
                existingCampaign.advertiserList
            } else {
                createCampaignDTO.advertiserList
            },
            adcontentList = if (createCampaignDTO.adcontentList == null) {
                existingCampaign.adcontentList
            } else {
                createCampaignDTO.adcontentList
            }
        )
        val campaign = campaignRepository.save(updatedCampaign.toCreateCampaignDTO().toEnitty())
        return campaign.toReadCampaignDTO()
    }

    //id로 삭제
    fun deleteCampaignById(
        @PathVariable(value = "id") campaignId: Long
    ) {
        val campaign = findCampaignByIdOrThrowNotFound(campaignId)
        campaignRepository.delete(campaign)
    }

    private fun findCampaignByIdOrThrowNotFound(id: Long): Campaign {
        val campaign = campaignRepository.findByIdOrNull(id)
        if (campaign == null) {
            throw NotFoundException()
        } else {
            return campaign;
        }
    }
}