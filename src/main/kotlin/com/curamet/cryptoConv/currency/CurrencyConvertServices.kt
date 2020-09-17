package com.curamet.cryptoConv.currency

import com.curamet.cryptoConv.currency.dto.CurrencyConvertDTO
import com.curamet.cryptoConv.currency.dto.ExchangeDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CurrencyConvertServices {
    private var jacksonMapper = ObjectMapper()
    private val restTemplate = RestTemplate()
    private val interceptors: MutableList<ClientHttpRequestInterceptor> = mutableListOf(
            HeaderRequestInterceptor("X-CoinAPI-Key", "14E0E983-278C-44D0-991F-4E1C1A9E2771"),
            HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE)
    )

    /**
     * get conversion rate for crypto exchange objects
     */
    fun getConvertRate(currencyConvertDTO: CurrencyConvertDTO): Double? {
        try {
            var rateURL = "https://rest.coinapi.io/v1/exchangerate/${currencyConvertDTO.asset_id_base}/${currencyConvertDTO.asset_id_quote}"
            println(rateURL)
            restTemplate.interceptors = interceptors
            val response: CurrencyConvertDTO? = restTemplate.getForObject(rateURL,
                    CurrencyConvertDTO::class)
            println("Conversion details: $response")
            return response?.rate
        } catch (e: Exception) {
            println("error|$e")
            return 0.0
        }
    }

    /***
     * get crypt currency list from one provider
     */
    fun getCurrenciesList(): MutableList<ExchangeDTO> {
        try {
            restTemplate.interceptors = interceptors
            //Get currencies list from all exchange IDs
            val response: String? = restTemplate.getForObject("https://rest.coinapi.io/v1/assets", String::class)

            //Jackson Mapper for JSON deserialization
            var result = jacksonMapper.readValue<MutableList<ExchangeDTO>>(response.toString())
            //result = result.filter { it.exchange_id == "COINEXCHANGE" }.toMutableList().sortedWith(compareBy { it.asset_id_base }).toMutableList()
            return result
        } catch (e: java.lang.Exception) {
            println(e)
            return mutableListOf()
        }
    }


    inner class HeaderRequestInterceptor(private val headerName: String, private val headerValue: String) : ClientHttpRequestInterceptor {

        @Throws(IOException::class)
        override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
            request.headers.set(headerName, headerValue)
            return execution.execute(request, body)
        }
    }

}