package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsApiReceiptDto : FnsReceiptDto() {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "utc")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    override var dateTime: LocalDateTime? = null // 1559062560

    companion object {
        private val objectMapper = ObjectMapper()
                .registerModule(KotlinModule())
                .registerModule(JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        @JvmStatic
        fun fromString(string: String): FnsApiReceiptDto = objectMapper.readValue(string, FnsApiReceiptDto::class.java)
    }
}
