package com.business.ad.repository

import com.business.ad.model.Adcontent
import com.business.ad.model.Advertiser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//레포지토리: 광고 내용
@Repository
interface AdcontentRepository : JpaRepository<Adcontent, Long?> {

    fun findByContent(Content: String?): List<Adcontent>
}