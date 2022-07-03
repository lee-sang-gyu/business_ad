package com.business.ad.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.business.ad.model.Result

//레포지토리: 캠페인 결과
@Repository
interface ResultRepository : JpaRepository<Result, Long?> {
}