package space.shefer.receipt.fns.dto

class FnsItemDto {
    var name: String = ""  // Полное название товара
    var price: Int = 0   // Стоитость товара в копейках
    var quantity: Double = 0.0  // Количество
    var sum: Int = 0  // Сумма в копейках
    var storno: Boolean = false // false
}
