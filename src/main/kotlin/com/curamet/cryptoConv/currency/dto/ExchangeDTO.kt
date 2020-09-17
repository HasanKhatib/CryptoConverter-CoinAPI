package com.curamet.cryptoConv.currency.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ExchangeDTO {
    var symbol_id: String? = ""
    var exchange_id: String? = ""
    var symbol_type: String? = ""
    var asset_id : String = ""
    var type_is_crypto : String = ""
    var name : String = ""
    var asset_id_base: String? = ""
    var asset_id_quote: String? = ""
    var option_type_is_call: String? = ""
    var option_strike_price: Double? = 0.0
    var option_contract_unit: Double? = 0.0
    var option_exercise_style: String? = ""
    var option_expiration_time: String? = ""
    var data_start: String? = ""
    var data_trade_start : String? = ""
    var data_trade_end : String? = ""
    var data_end: String? = ""
    var data_quote_start: String? = ""
    var data_quote_end: String? = ""
    var data_orderbook_start: String? = ""
    var data_orderbook_end: String? = ""
    var future_delivery_time : String? = ""
    var volume_1hrs: Double? = 0.0
    var volume_1hrs_usd: Double? = 0.0
    var volume_1day: Double? = 0.0
    var volume_1day_usd: Double? = 0.0
    var volume_1mth: Double? = 0.0
    var volume_1mth_usd: Double? = 0.0
}