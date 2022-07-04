package com.business.ad.service

import com.business.ad.dto.*
import com.business.ad.error.NotFoundException
import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import com.business.ad.model.Result
import com.business.ad.repository.AdcontentRepository
import com.business.ad.repository.AdvertiserRepository
import com.business.ad.repository.CampaignRepository
import com.business.ad.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import java.util.regex.Pattern

//서비스: 캠페인 결과
@Component
class ResultService {
    @Autowired
    lateinit var resultRepository: ResultRepository
    @Autowired
    lateinit var campaignRepository: CampaignRepository
    @Autowired
    lateinit var adcontentRepository: AdcontentRepository
    @Autowired
    lateinit var advertiserRepository: AdvertiserRepository

    //결과 전체 조회
    fun getResult(): List<ReadResultDTO> {
        val result = resultRepository.findAll()
        return result.map { it.toReadResultDTO() }
    }

    //결과 id로 조회
    fun getResultById(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultDTO {
        val result = findResultByIdOrThrowNotFound(resultId)
       return result.toReadResultDTO()
    }

    //결과 조회: 캠페인 정보 포함
    fun getResultJoinCampaign(): List<ReadResultJoinCampaignDTO> {

        val result = resultRepository.findAll()
        val resultList = mutableListOf<ReadResultJoinCampaignDTO>()
        result.forEach {

            val campaign = campaignRepository.findById(it.campaignid)

            if (campaign.isEmpty == false) {

                if (campaign.get().advertiserId == null) {
                    campaign.get().advertiserId = ""
                }
                if (campaign.get().adcontentId == null) {
                    campaign.get().adcontentId = ""
                }
                //광고주
                val arr_adv_id = Pattern.compile("/").split(campaign.get().advertiserId)
                val arr_long_adv_id = mutableListOf<Long>()
                if (arr_adv_id.get(0) != "") {
                    arr_adv_id.forEach {
                        arr_long_adv_id.add(it.replace("$", "").toLong())
                    }
                }
                val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)
                //광고 내용
                val arr_adc_id = Pattern.compile("/").split(campaign.get().adcontentId)
                val arr_long_adc_id = mutableListOf<Long>()
                if (arr_adc_id.get(0) != "") {
                    arr_adc_id.forEach {
                        arr_long_adc_id.add(it.replace("$", "").toLong())
                    }
                }
                val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)

                val ad_join = convertCampaignResponse(campaign.get(), advertiser,adcontent)
                resultList.add(convertResultResponse(it, ad_join))
            }else{
                resultList.add(convertResultResponse(it, null))
            }

        }
        return resultList
    }

    //id로 조회 조회: 광고주, 광고내용 포함
    fun getResultJoinCampaignById(
        @PathVariable(value = "id") resultId: Long
    ): ReadResultJoinCampaignDTO {
        val result = findResultByIdOrThrowNotFound(resultId)
        val campaign = campaignRepository.findById(result.campaignid)
        if (campaign.isEmpty == false) {
            if (campaign.get().advertiserId == null) {
                campaign.get().advertiserId = ""
            }
            if (campaign.get().adcontentId == null) {
                campaign.get().adcontentId = ""
            }
            //광고주
            val arr_adv_id = Pattern.compile("/").split(campaign.get().advertiserId)
            val arr_long_adv_id = mutableListOf<Long>()
            if (arr_adv_id.get(0) != "") {
                arr_adv_id.forEach {
                    arr_long_adv_id.add(it.replace("$", "").toLong())
                }
            }
            val advertiser = advertiserRepository.findByIdIn(arr_long_adv_id)
            //광고 내용
            val arr_adc_id = Pattern.compile("/").split(campaign.get().adcontentId)
            val arr_long_adc_id = mutableListOf<Long>()
            if (arr_adc_id.get(0) != "") {
                arr_adc_id.forEach {
                    arr_long_adc_id.add(it.replace("$", "").toLong())
                }
            }
            val adcontent = adcontentRepository.findByIdIn(arr_long_adc_id)

            val ad_join = convertCampaignResponse(campaign.get(), advertiser,adcontent)
            return convertResultResponse(result, ad_join)
        } else  {
            return convertResultResponse(result, null)
        }

    }

    //광고 결과 생성
    fun createResult(createResultDTO: CreateResultDTO): CreateResultDTO {
        val result = resultRepository.save(createResultDTO.toEnitty())
        return result.toCreateResultDTO()
    }

    //광고 결과 변경
    fun updateResultById(
        @PathVariable(value = "id") resultId: Long,
        createResultDTO: CreateResultDTO
    ): ReadResultDTO {
        val existingResult = findResultByIdOrThrowNotFound(resultId)
        var updatedResult = existingResult.copy(
            campaignid = createResultDTO.campaignid,
            cliks = createResultDTO.cliks,
            exposure = createResultDTO.exposure
        )
        val result = resultRepository.save(updatedResult.toCreateResultDTO().toEnitty())
        return result.toReadResultDTO()
    }

    //광고 결과 삭제
    fun deleteResultById(
        @PathVariable(value = "id") resultId: Long
    ) {
        val campaign = findResultByIdOrThrowNotFound(resultId)
        resultRepository.delete(campaign)
    }

    private fun convertResultResponse(
        result: Result,
        join_campaign: ReadCampaignJoinAdDTO?
    ): ReadResultJoinCampaignDTO {
        return ReadResultJoinCampaignDTO(
            id = result.id,
            campaignid = result.campaignid,
            cliks = result.cliks,
            exposure = result.exposure,
            campaign = join_campaign
        )
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
            adcontentId = campaign.adcontentId,
            advertiser = join_advertiser,
            adcontent = join_adcontent
        )
    }

    private fun findResultByIdOrThrowNotFound(id: Long): Result {
        val result = resultRepository.findByIdOrNull(id)
        if (result == null) {
            throw NotFoundException()
        } else {
            return result;
        }
    }
}