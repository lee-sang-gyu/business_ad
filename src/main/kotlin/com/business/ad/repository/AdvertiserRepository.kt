package com.business.ad.repository

import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//레포지토리: 광고주 정보
@Repository
interface AdvertiserRepository : JpaRepository<Advertiser, Long?> {

    fun findAllBy(): List<Advertiser>
    fun findByIdIn(Id: List<Long>): List<Advertiser>
}