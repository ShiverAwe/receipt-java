package space.shefer.receipt.fnssdk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class FnsItemDto {
    var name: String = ""  // Полное название товара
    var price: Int = 0   // Стоитость товара в копейках
    var quantity: Double = 0.0  // Количество
    var sum: Int = 0  // Сумма в копейках
    var storno: Boolean = false // false
}
