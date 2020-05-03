package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsResponseDto {
    lateinit var document: FnsResponseDocumentDto

    companion object {
        private val objectMapper = ObjectMapper()
                .registerModule(KotlinModule())
                .registerModule(JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        @JvmStatic
        fun fromString(string: String): FnsResponseDto {
            return objectMapper.readValue(string, FnsResponseDto::class.java)
        }
    }
}
