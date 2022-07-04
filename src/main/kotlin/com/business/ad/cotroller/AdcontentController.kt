package com.business.ad.cotroller

import com.business.ad.dto.CreateAdcontentDTO
import com.business.ad.dto.ReadAdcontentDTO
import com.business.ad.model.WebResponse
import com.business.ad.service.AdcontentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//컨트롤러: 광고 내용
@RestController
@RequestMapping("/api")
class AdcontentController {
    @Autowired
    private lateinit var adcontentService: AdcontentService

    //광고내용 전체 조회
    @GetMapping(
        value = ["/adcontent"],
        produces = ["application/json"]
    )
    fun getAllAdcontent(): WebResponse<List<ReadAdcontentDTO>> {
        val Response = adcontentService.getAdcontent()
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //광고 내용 생성
    @PostMapping(
        value = ["/adcontent"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createAdcontent(@RequestBody createAdcontentDTO: CreateAdcontentDTO)
            : WebResponse<CreateAdcontentDTO> {
        val Response = adcontentService.createAdcontent(createAdcontentDTO)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //Id로 조회
    @GetMapping(
        value = ["/adcontent/id/{id}"],
        produces = ["application/json"]
    )
    fun getAdvertiserById(@PathVariable(value = "id") adcontentId: Long)
            : WebResponse<ReadAdcontentDTO> {
        val Response = adcontentService.getAdcontentById(adcontentId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //Id로 변경
    @PutMapping(
        value = ["/adcontent/id/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateAdcontentById(
        @PathVariable(value = "id") adcontentId: Long,
        @RequestBody updateAdcontent: CreateAdcontentDTO
    ): WebResponse<ReadAdcontentDTO> {
        val Response = adcontentService.updateAdcontentById(adcontentId, updateAdcontent)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //Id로 삭제
    @DeleteMapping(
        value = ["/adcontent/id/{id}"],
        produces = ["application/json"]
    )
    fun deleteAdcontentById(@PathVariable(value = "id") adcontentId: Long)
            : WebResponse<Long> {
        adcontentService.deleteAdcontentById(adcontentId)
        return WebResponse(
            code = 200,
            status = "OK",
            data = adcontentId
        )
    }

    //내용 항목으로 조회
    @GetMapping(
        value = ["/adcontent/{content}"],
        produces = ["application/json"]
    )
    fun getAdcontentByContent(@PathVariable(value = "content") adContent: String)
            : WebResponse<List<ReadAdcontentDTO>> {

        val Response = adcontentService.getAdcontentByContent(adContent)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //광고 내용 이름만 변경
    @PutMapping(
        value = ["/adcontent/{content}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateOnlyContentByContent(
        @PathVariable(value = "content") adContent: String,
        @RequestBody updateAdcontent: ReadAdcontentDTO
    ): WebResponse<List<ReadAdcontentDTO>> {
        val Response = adcontentService.updateOnlyContentByContent(adContent, updateAdcontent)
        return WebResponse(
            code = 200,
            status = "OK",
            data = Response
        )
    }

    //내용 항목으로 삭제
    @DeleteMapping(
            value = ["/adcontent/{content}"],
        produces = ["application/json"]
    )
    fun deleteOnlyContentByContent(
        @PathVariable(value = "content") adContent: String
    ): WebResponse<String> {
        adcontentService.deleteOnlyContentByContent(adContent)
        return WebResponse(
            code = 200,
            status = "OK",
            data = adContent
        )
    }
}