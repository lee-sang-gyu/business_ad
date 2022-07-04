package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.error.NotFoundException
import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import com.business.ad.repository.AdcontentRepository
import com.business.ad.repository.AdvertiserRepository
import com.business.ad.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.regex.Pattern

//서비스: 캠페인 정보
@Component
class CampaignService {
    @Autowired
    lateinit var campaignRepository: CampaignRepository

    @Autowired
    lateinit var advertiserRepository: AdvertiserRepository

    @Autowired
    lateinit var adcontentRepository: AdcontentRepository

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
        /*return if (campaign.isEmpty) {
            ReadCampaignDTO(campaignId, "", null, null, 0, "", "")
        } else {
            campaign.get().toReadCampaignDTO()
        }*/
    }

    //전체 조회: 광고주, 광고내용 포함
    fun getCampaignJoinAd(): List<ReadCampaignJoinAdDTO> {

        val campaign = campaignRepository.findAll()
        val campaignList = mutableListOf<ReadCampaignJoinAdDTO>()
        campaign.forEach {
            if (it.advertiserId == null) {
                it.advertiserId = ""
            }
            if (it.adcontentId == null) {
                it.adcontentId = ""
            }
            //광고주
            val arr_adv_id = Pattern.compile("/").split(it.advertiserId)
            val arr_long_adv_id = mutableListOf<Long>()
            if (arr_adv_id.get(0) != "") {
                arr_adv_id.forEach {
                    arr_long_adv_id.add(it.replace("$", "").toLong())
                }
            }
            //광고 내용
            val arr_adc_id = Pattern.compile("/").split(it.adcontentId)
            val arr_long_adc_id = mutableListOf<Long>()
            if (arr_adc_id.get(0) != "") {
                arr_adc_id.forEach {
                    arr_long_adc_id.add(it.replace("$", "").toLong())
                }
            }
            val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)
            val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
            campaignList.add(convertCampaignResponse(it, advertiser, adcontent))
        }
        return campaignList
    }

    //id로 조회 조회: 광고주, 광고내용 포함
    fun getCampaignJoinAdById(
        @PathVariable(value = "id") campaignId: Long
    ): ReadCampaignJoinAdDTO {

        val campaign = findCampaignByIdOrThrowNotFound(campaignId)
        if (campaign.advertiserId == null) {
            campaign.advertiserId = ""
        }
        if (campaign.adcontentId == null) {
            campaign.adcontentId = ""
        }
        //광고주
        val arr_adv_id = Pattern.compile("/").split(campaign.advertiserId)

        val arr_long_adv_id = mutableListOf<Long>()
        if (arr_adv_id.get(0) != "") {
            arr_adv_id.forEach {
                arr_long_adv_id.add(it.replace("$", "").toLong())
            }
        }
        val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)

        //광고 내용
        val arr_adc_id = Pattern.compile("/").split(campaign.adcontentId)
        val arr_long_adc_id = mutableListOf<Long>()
        if (arr_adc_id.get(0) != "") {
            arr_adc_id.forEach {
                arr_long_adc_id.add(it.replace("$", "").toLong())
            }
        }
        val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
        return convertCampaignResponse(campaign, advertiser, adcontent)
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
            name = createCampaignDTO.name,
            startDate = createCampaignDTO.startDate,
            endDate = createCampaignDTO.endDate,
            subjectList = createCampaignDTO.subjectList,
            advertiserId = createCampaignDTO.advertiserId,
            adcontentId = createCampaignDTO.adcontentId
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

    private fun convertCampaignResponse(
        campaign: Campaign,
        join_advertiser: List<Advertiser>,
        join_adcontent: List<Adcontent>
    ): ReadCampaignJoinAdDTO {
        return ReadCampaignJoinAdDTO(
            id = campaign.id,
            name = campaign.name,
            startDate = campaign.startDate,
            endDate = campaign.endDate,
            subjectList = campaign.subjectList,
            advertiserId = campaign.advertiserId,
            advertiser = join_advertiser,
            adcontentId = campaign.adcontentId,
            adcontent = join_adcontent
        )
    }
}