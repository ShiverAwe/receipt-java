package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsApiReceiptDto : FnsReceiptDto() {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "utc")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    override var dateTime: LocalDateTime? = null
}
