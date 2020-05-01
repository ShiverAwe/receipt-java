package space.shefer.receipt.fnssdk.dto

import org.junit.Test
import space.shefer.receipt.tests.util.ResourceUtil
import junit.framework.TestCase.assertEquals


class FnsReceiptDtoTest {

    @Test
    fun testParsingOk() {
        val receiptString = ResourceUtil.getResourceAsString("/fns/receipt.json", javaClass)

        val receiptDto = FnsReceiptDto.fromString(receiptString)

        assertEquals(1559062560, receiptDto.dateTime)
        assertEquals(29049, receiptDto.fiscalDocumentNumber)
        assertEquals("87250982347532", receiptDto.fiscalDriveNumber)
        assertEquals(2869721424, receiptDto.fiscalSign)
        assertEquals(2, receiptDto.items.size)
        // TODO continue test
    }

}
