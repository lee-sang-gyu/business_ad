package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.validation.ValidationUtil
import com.business.ad.error.NotFoundException
import com.business.ad.model.Advertiser
import com.business.ad.repository.AdcontentRepository
import com.business.ad.repository.AdvertiserRepository
import com.business.ad.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable

//서비스: 광고주 정보
@Component
class AdvertiserService {
    @Autowired
    lateinit var validationUtil: ValidationUtil

    @Autowired
    lateinit var advertiserRepository: AdvertiserRepository

    @Autowired
    lateinit var adcontentRepository: AdcontentRepository

    @Autowired
    lateinit var campaignRepository: CampaignRepository

    fun getAdvertisers(): List<ReadAdvertiserDTO> {
        val advertiser = advertiserRepository.findAll()
        return advertiser.map { it.toReadAdvertiserDTO() }
    }

    //광고주 생성
    fun createAdvertiser(createAdvertiserDTO: CreateAdvertiserDTO): CreateAdvertiserDTO {
        validationUtil.validate(createAdvertiserDTO)
        val advertiser = advertiserRepository.save(createAdvertiserDTO.toEnitty())
        return advertiser.toCreateAdvertiserDTO()
    }

    //광고주 id로 조회
    fun getAdvertiserById(@PathVariable(value = "id") advertiserId: Long): ReadAdvertiserDTO {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        return advertiser.toReadAdvertiserDTO()
    }

    //광고주 조회: 캠페인 포함
    fun getAdvertiserByIdGetCampaign(
        @PathVariable(value = "id") advertiserId: Long
    ): ReadAdvertiserGetCamapignDTO {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        val campaign = campaignRepository.findByAdvertiserListId(advertiserId)
        return ReadAdvertiserGetCamapignDTO(advertiser.id, advertiser.name, campaign.map { it.toOnlyCampaignDTO() })
    }

    //광고주 조회: 캠페인(광고내용) 포함
    fun getAdvertiserByIdGetCampaignAndContent(
        @PathVariable(value = "id") advertiserId: Long
    ): ReadAdvertiserGetCamapignAndContentDTO {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        val campaign = campaignRepository.findByAdvertiserListId(advertiserId)

        return ReadAdvertiserGetCamapignAndContentDTO(
            advertiser.id,
            advertiser.name,
            campaign.map { it.toCampaignGetContentDTO() })
    }
    //광고주 조회: 캠페인 포함 - 세부목록
    /*fun getAdvertiserByIdJoinCampaignDetail(
        @PathVariable(value = "id") advertiserId: Long
    ): ReadAdvertiserJoinCamapignDetailDTO {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        val like_adv: String = "%$" + advertiserId + "/%"
        val campaign = campaignRepository.findByAdvertiserIdLike(like_adv)
        val campaignList = mutableListOf<ViewCampaignAddContentDTO>()
        campaign.forEach {

            //광고 내용
            val arr_adc_id = Pattern.compile("/").split(it.adcontentId)
            if (it.adcontentId == null) {
                it.adcontentId = ""
            }
            val arr_long_adc_id = mutableListOf<Long>()
            if (arr_adc_id.get(0) != "") {
                arr_adc_id.forEach {
                    arr_long_adc_id.add(it.replace("$", "").toLong())
                }
            }
            val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)
            campaignList.add(convertCampaignResponse(it, adcontent))
        }


        return ReadAdvertiserJoinCamapignDetailDTO(advertiser.id, advertiser.name, campaignList)
    }*/

    //광고주 변경
    fun updateAdvertiserById(
        @PathVariable(value = "id") advertiserId: Long,
        createAdvertiserDTO: CreateAdvertiserDTO
    ): CreateAdvertiserDTO {
        val existingAdvertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        validationUtil.validate(createAdvertiserDTO)
        var updatedAdvertiser = existingAdvertiser.copy(name = createAdvertiserDTO.name)
        advertiserRepository.save(updatedAdvertiser.toCreateAdvertiserDTO().toEnitty())
        return updatedAdvertiser.toCreateAdvertiserDTO()
    }

    //광고주 삭제
    fun deleteAdvertiserById(@PathVariable(value = "id") advertiserId: Long) {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        advertiserRepository.delete(advertiser)
    }

    private fun findAdvertiserByIdOrThrowNotFound(id: Long): Advertiser {
        val advertiser = advertiserRepository.findByIdOrNull(id)
        if (advertiser == null) {
            throw NotFoundException()
        } else {
            return advertiser;
        }
    }

    /* private fun convertCampaignResponse(
         campaign: Campaign,
         join_adcontent: List<Adcontent>
     ): ViewCampaignAddContentDTO {
         return ViewCampaignAddContentDTO(
             id = campaign.id,
             name = campaign.name,
             startDate = campaign.startDate,
             endDate = campaign.endDate,
             subjectList = campaign.subjectList,
             adcontent = join_adcontent
         )
     }*/
}