package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import com.business.ad.repository.AdcontentRepository
import com.business.ad.repository.AdvertiserRepository
import com.business.ad.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable

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
        val campaign = campaignRepository.findById(campaignId)
        return if (campaign.isEmpty) {
            ReadCampaignDTO(campaignId, "", null, null, 0, "", "")
        } else {
            campaign.get().toReadCampaignDTO()
        }
    }

    //전체 조회: 광고주, 광고내용 포함
    fun getCampaignJoinAd(): List<ReadCampaignJoinAdDTO> {

        val campaign = campaignRepository.findAll()
        val campaignList = mutableListOf<ReadCampaignJoinAdDTO>()
        campaign.forEach {
            //광고주
            val arr_adv_id = it.advertiser_id.split("/")
            val arr_long_adv_id = mutableListOf<Long>()
            if (arr_adv_id.get(0) != "") {
                arr_adv_id.forEach { arr_long_adv_id.add(it.toLong()) }
            }
            val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)
            //광고 내용
            val arr_adc_id = it.adcontent_id.split("/")
            val arr_long_adc_id = mutableListOf<Long>()
            if (arr_adc_id.get(0) != "") {
                arr_adc_id.forEach { arr_long_adc_id.add(it.toLong()) }
            }
            val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
            campaignList.add(convertCampaignResponse(it, advertiser, adcontent))
        }
        return campaignList
    }

    //id로 조회 조회: 광고주, 광고내용 포함
    fun getCampaignJoinAdById(
        @PathVariable(value = "id") campaignId: Long
    ): ReadCampaignJoinAdDTO {

        val campaign = campaignRepository.findById(campaignId)
        //광고주
        val arr_adv_id = campaign.get().advertiser_id.split("/")
        val arr_long_adv_id = mutableListOf<Long>()
        if (arr_adv_id.get(0) != "") {
            arr_adv_id.forEach { arr_long_adv_id.add(it.toLong()) }
        }
        val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)
        //광고 내용
        val arr_adc_id = campaign.get().adcontent_id.split("/")
        val arr_long_adc_id = mutableListOf<Long>()
        if (arr_adc_id.get(0) != "") {
            arr_adc_id.forEach { arr_long_adc_id.add(it.toLong()) }
        }
        val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
        return convertCampaignResponse(campaign.get(), advertiser, adcontent)
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
        val existingCampaign = campaignRepository.findById(campaignId)

        return if (existingCampaign.isEmpty) {
            ReadCampaignDTO(campaignId, "", null, null, 0, "", "")
        } else {
            var updatedCampaign = existingCampaign.get().copy(
                name = createCampaignDTO.name,
                start_date = createCampaignDTO.start_date,
                end_date = createCampaignDTO.end_date,
                subject_list = createCampaignDTO.subject_list,
                advertiser_id = createCampaignDTO.advertiser_id,
                adcontent_id = createCampaignDTO.adcontent_id
            )
            val campaign = campaignRepository.save(updatedCampaign.toCreateCampaignDTO().toEnitty())
            campaign.toReadCampaignDTO()
        }
    }

    //id로 삭제
    fun deleteCampaignById(
        @PathVariable(value = "id") campaignId: Long
    ): Boolean {
        val campaign = campaignRepository.findById(campaignId)
        return if (campaign.isEmpty) {
            return false
        } else {
            campaign.map { deleteCampaign -> campaignRepository.delete(deleteCampaign) }
            val checkCampaign = campaignRepository.findById(campaignId)
            return checkCampaign.isEmpty
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
            start_date = campaign.start_date,
            end_date = campaign.end_date,
            subject_list = campaign.subject_list,
            advertiser_id = campaign.advertiser_id,
            advertiser = join_advertiser,
            adcontent_id = campaign.adcontent_id,
            adcontent = join_adcontent
        )
    }
}