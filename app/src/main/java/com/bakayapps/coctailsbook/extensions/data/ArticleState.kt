package com.bakayapps.coctailsbook.extensions.data

import com.bakayapps.coctailsbook.data.AppSettings
import com.bakayapps.coctailsbook.data.CoctailPersonalInfo
import com.bakayapps.coctailsbook.viewmodels.CoctailState

fun CoctailState.toAppSettings() : AppSettings {
    return AppSettings(isDarkMode)
}

fun CoctailState.toArticlePersonalInfo(): CoctailPersonalInfo {
    return CoctailPersonalInfo(isLike, isBookmark)
}