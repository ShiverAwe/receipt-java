package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.time.LocalDateTime
import java.time.ZoneOffset

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsAppReceiptDto : FnsReceiptDto() {
    override var dateTime: LocalDateTime?
        @JsonIgnore
        get() = dateTime_?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
        @JsonIgnore
        set(value) {
            if (value != null) {
                dateTime_ = value.toEpochSecond(ZoneOffset.UTC)
            }
        }

    @JsonProperty("dateTime")
    var dateTime_: Long? = 0 // 1559062560

    companion object {
        private val objectMapper = ObjectMapper()
                .registerModule(KotlinModule())
                .registerModule(JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        @JvmStatic
        fun fromString(string: String): FnsAppReceiptDto = objectMapper.readValue(string, FnsAppReceiptDto::class.java)
    }

}
