package com.business.ad.repository

import com.business.ad.model.Advertiser
import com.business.ad.model.Campaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

//레포지토리: 캠페인 정보
@Repository
interface CampaignRepository : JpaRepository<Campaign, Long?> {

    fun findByAdvertiseridLike(Advertiserid: String): List<Campaign>
    fun findByNameLike(Name: String): List<Campaign>
}