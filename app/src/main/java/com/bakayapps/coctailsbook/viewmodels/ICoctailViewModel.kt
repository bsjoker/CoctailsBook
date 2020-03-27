package com.bakayapps.coctailsbook.viewmodels

import androidx.lifecycle.LiveData
import com.bakayapps.coctailsbook.data.CoctailData
import com.bakayapps.coctailsbook.data.CoctailPersonalInfo
import com.bakayapps.coctailsbook.data.ShortItemCoctail

interface ICoctailViewModel {
    /**
     * Получение полной информации о статье из сети
     * (или базы данных если она сохранена, наличие статьи в базе не надо реализовывать в данном уроке)
     */
    fun getCoctailRecipe(): LiveData<List<Any>?>

    /**
     * Получение краткой информации о статье из базы данных
     */
    fun getCoctailData(): LiveData<CoctailData?>

    /**
     * Получение списка коктейлей по категории
     */
    fun getShortItemCoctailsByCategory(): LiveData<List<ShortItemCoctail>?>

    /**
     * Получение пользовательской информации о статье из базы данных
     */
    fun getCoctailPersonalInfo(): LiveData<CoctailPersonalInfo?>

    /**
     * Получение настроек приложения
     */
    fun handleNightMode()

    /**
     * добавление/удалние статьи в закладки, обрабока нажатия на кнопку btn_bookmark
     * необходимо отобразить сообщение пользователю "Add to bookmarks" или "Remove from bookmarks"
     * в соответствии с текущим состоянием
     */
    fun handleBookmark()

    /**
     * добавление/удалние статьи в понравившееся, обрабока нажатия на кнопку btn_like
     * необходимо отобразить сообщение пользователю (Notify.ActionMessage) "Mark is liked" или
     * "Don`t like it anymore"  в соответствии с текущим состоянием.
     * если пользователь убрал Like необходимо добавить  actionLabel в снекбар
     * "No, still like it" при нажатиии на который состояние вернется к isLike = true
     */
    fun handleLike()

    /**
     * поделиться статьей, обрабока нажатия на кнопку btn_share
     * необходимо отобразить сообщение с ошибкой пользователю (Notify.ErrorMessage) "Share is not implemented"
     * и текстом errLabel "OK"
     */
    fun handleShare()

//    /**
//     * обрабока нажатия на кнопку btn_settings
//     * необходимо отобразить или скрыть меню в соответствии с текущим состоянием
//     */
//    fun handleToggleMenu()

    /**
     * обрабока перехода в режим поиска searchView
     * при нажатии на пункту меню тулбара необходимо отобразить searchView и сохранить состояние при
     * изменении конфигурации (пересоздании активити)
     */
    fun handleSearchMode(isSearch: Boolean)

    /**
     * обрабока поискового запроса, необходимо сохранить поисковый запрос и отображать его в
     * searchView при изменении конфигурации (пересоздании активити)
     */
    fun handleSearch(query: String?)
}