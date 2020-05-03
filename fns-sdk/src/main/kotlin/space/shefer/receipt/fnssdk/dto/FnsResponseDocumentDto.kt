package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsResponseDocumentDto {
    lateinit var receipt: FnsApiReceiptDto
}
