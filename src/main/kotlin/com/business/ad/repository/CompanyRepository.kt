package com.business.ad.repository

import com.business.ad.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long?> {

    fun findByName(Name: String): Company?
}