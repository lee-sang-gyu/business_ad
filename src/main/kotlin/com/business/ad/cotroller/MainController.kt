package com.business.ad.cotroller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {
    @RequestMapping("/index")
    @ResponseBody
    fun Index(): String {
        return "TEST"
    }
}