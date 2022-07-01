package com.business.ad.repository

import com.business.ad.model.Advertiser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdvertiserRepository : JpaRepository<Advertiser, Long?> {

    fun findAllBy(): List<Advertiser>
    //fun findByName(Name: String): Advertiser?
}