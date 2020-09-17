package com.curamet.cryptoConv.currency

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import org.springframework.ui.set


@Controller
@RequestMapping("/")
class CurrencyConvertController {

    @RequestMapping("/")
    fun home(model: Model): String {
        var result = CurrencyConvertServices().getCurrenciesList()
        if(result.isEmpty())
            model["error"] = "Error while retrieving currencies!"
        else
            model["error"] = ""
        model["symbols"] = result
        return "views/conversion"
    }
}