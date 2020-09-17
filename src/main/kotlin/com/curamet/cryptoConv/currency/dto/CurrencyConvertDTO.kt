package com.curamet.cryptoConv.currency.dto

class CurrencyConvertDTO (var asset_id_base: String, var asset_id_quote: String, var amount : Double){
    var rate: Double = 0.0
    var conversionRate: Double = 0.0
    var time: String = ""
    var quote_result :Double = 0.0
    override fun toString(): String {
        return "From: $asset_id_base, To: $asset_id_quote, Amount: $amount, Result: $quote_result"
    }
}