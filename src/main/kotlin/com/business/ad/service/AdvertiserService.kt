package com.business.ad.service

import com.business.ad.validation.ValidationUtil
import com.business.ad.dto.CreateAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserDTO
import com.business.ad.dto.ReadAdvertiserJoinCamapignDTO
import com.business.ad.error.NotFoundException
import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
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
    fun getAdvertiserJoinCampaignById(
        @PathVariable(value = "id") advertiserId: Long
    ): ReadAdvertiserJoinCamapignDTO {
        val advertiser = findAdvertiserByIdOrThrowNotFound(advertiserId)
        val like_adv: String = "%$" + advertiserId + "/%"
        val campaign = campaignRepository.findByAdvertiseridLike(like_adv)
        return ReadAdvertiserJoinCamapignDTO(advertiser.id, advertiser.name, campaign)
    }


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
}