package space.shefer.receipt.fns.dto

data class FnsItemDto(
        val name: String, // Полное название товара
        val price: Int, // Стоитость товара в копейках
        val quantity: Double, // Количество
        val sum: Int, // Сумма в копейках
        val storno: Boolean // false
)
