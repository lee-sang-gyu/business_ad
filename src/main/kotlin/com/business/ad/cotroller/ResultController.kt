package com.business.ad.cotroller

import com.business.ad.dto.CreateResultDTO
import com.business.ad.service.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 캠페인 결과
@RestController
@RequestMapping("/api")
class ResultController {
    @Autowired
    private lateinit var resultService: ResultService

    //모든 캠페인 결과 조회
    @GetMapping("/result")
    fun getAllResult(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(resultService.getResult())
    }

    //캠페인 결과 생성
    @PostMapping("/result")
    fun createResult(@RequestBody createResultDTO: CreateResultDTO)
            : ResponseEntity<Any> {
        resultService.createResult(createResultDTO)
        return ResponseEntity.ok().body(true)
    }

    //캠페인 결과 Id로 변경
    @PutMapping("/result/{id}")
    fun updateResultById(
        @PathVariable(value = "id") resultId: Long,
        @RequestBody updateResult: CreateResultDTO
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(resultService.updateResultById(resultId, updateResult))
    }

    //캠페인 결과 Id로 삭제
    @DeleteMapping("/result/{id}")
    fun deleteResultById(@PathVariable(value = "id") resultId: Long)
            : ResponseEntity<Any> {
        return ResponseEntity.ok().body(resultService.deleteResultById(resultId))
    }
}