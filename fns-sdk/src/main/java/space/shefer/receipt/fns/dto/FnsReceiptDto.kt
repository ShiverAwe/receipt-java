package space.shefer.receipt.fns.dto

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

data class FnsReceiptDto(
        val cashTotalSum: Int, // 0
        val dateTime: Int, // 1559062560
        val discount: Double?, // null
        val discountSum: Double?, // null
        val ecashTotalSum: Int, // 66430
        /**
         * Фискальный Документ. ФД.
         */
        val fiscalDocumentNumber: Int, // 28649
        /**
         * Фискальный Номер. ФН.
         */
        val fiscalDriveNumber: String, // 9280440300037581
        /**
         * Фискальный признак. ФП.
         */
        val fiscalSign: Long, // 2869721424
        val items: List<FnsItemDto>, // Список товаров
        val kktNumber: String?, // null
        val kktRegId: String, // 0000270725037066
        val nds0: Int?, // null
        val nds10: Int?, // 4706
        val nds18: Int?, // 2445
        val ndsCalculated10: Int?, // null
        val ndsCalculated18: Int?, // null
        val ndsNo: Int?, // null
        val operationType: Int, // 1
        val `operator`: String, // Пикина 97283.
        val requestNumber: Int, // 172
        val retailPlaceAddress: String?, // 197341, Санкт-Петербург, б-р. Серебристый, д. 19, к. 1
        val shiftNumber: Int, // 57
        val taxationType: Int, // 1
        val totalSum: Int, // 66430
        val user: String?, // ООО "ТД Интерторг"
        val userInn: String? // 7842005813
) {
    companion object {
        private val objectMapper = ObjectMapper()
                .registerModule(KotlinModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        @JvmStatic
        fun fromString(string: String): FnsReceiptDto = objectMapper.readValue(string, FnsReceiptDto::class.java)
    }
}
