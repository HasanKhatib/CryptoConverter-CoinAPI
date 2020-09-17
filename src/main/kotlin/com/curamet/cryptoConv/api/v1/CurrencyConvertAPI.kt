package com.curamet.cryptoConv.api.v1

import com.curamet.cryptoConv.currency.CurrencyConvertServices
import com.curamet.cryptoConv.currency.dto.CurrencyConvertDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping(("/api/v1"))
class CurrencyConvertAPI {
    var jacksonMapper = ObjectMapper()

    @GetMapping("/convert")
    fun getConversionResult(@RequestParam(value = "from", defaultValue = "text") from: String,
                            @RequestParam(value = "to", defaultValue = "text") to: String,
                            @RequestParam(value = "amount", defaultValue = "text") amount: Double): String {
        try {
            var msgs: MutableList<CurrencyConvertDTO> = mutableListOf<CurrencyConvertDTO>()
            var obj = CurrencyConvertDTO(from, to, amount)
            var rate = CurrencyConvertServices().getConvertRate(obj)

            obj.amount = obj.amount * rate!!
            return jacksonMapper.writeValueAsString(obj)
        } catch (e: Exception) {
            return "error|${e.message}"
        }
    }
}