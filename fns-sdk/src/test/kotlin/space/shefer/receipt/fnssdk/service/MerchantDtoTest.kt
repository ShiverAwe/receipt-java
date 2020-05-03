package space.shefer.receipt.fnssdk.service

import com.fasterxml.jackson.databind.ObjectMapper
import junit.framework.TestCase.assertEquals
import org.junit.Test
import space.shefer.receipt.fnssdk.primainform.MerchantSuggestions

internal class MerchantDtoTest {


    @Test
    fun testParsingOk() {

        val json = """
                 {
                 "suggestions":[
                     {
                        "value":"ООО \"ЛЕНТА\"","data":
                            {
                               "inn":"7814148471",
                               "permalink":"lenta-1037832048605-7814148471",
                               "ogrn":"1037832048605",
                               "address":"197374, САНКТ-ПЕТЕРБУРГ ГОРОД, САВУШКИНА УЛИЦА, ДОМ 112, ЛИТЕРА Б"
                            }
                     }
                               ]
                 }
                      """.trimMargin()

        val merchantSuggestions: MerchantSuggestions = ObjectMapper().readValue(json, MerchantSuggestions::class.java)

        assertEquals("ООО \"ЛЕНТА\"", merchantSuggestions.suggestions.get(0).value)
        assertEquals("7814148471", merchantSuggestions.suggestions.get(0).data.inn)
        assertEquals("197374, САНКТ-ПЕТЕРБУРГ ГОРОД, САВУШКИНА УЛИЦА, ДОМ 112, ЛИТЕРА Б", merchantSuggestions.suggestions.get(0).data.address)
        assertEquals("lenta-1037832048605-7814148471", merchantSuggestions.suggestions.get(0).data.permalink)
        assertEquals("1037832048605", merchantSuggestions.suggestions.get(0).data.ogrn)

    }

}
