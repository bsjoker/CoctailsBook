package com.bakayapps.coctailsbook.data.remote.response

import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.google.gson.annotations.SerializedName

data class ShortItemCoctailResponse (
    @SerializedName("drinks") val drinks: ArrayList<ShortItemCoctail>
)