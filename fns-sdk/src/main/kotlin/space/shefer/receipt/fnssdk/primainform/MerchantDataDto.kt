package space.shefer.receipt.fnssdk.primainform

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

open class MerchantDataDto{

    var inn: String = ""
    var permalink: String = ""
    var ogrn: String = ""
    var address: String = ""


}
