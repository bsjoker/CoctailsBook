package com.bakayapps.coctailsbook.database

import androidx.lifecycle.LiveData
import com.bakayapps.coctailsbook.data.CoctailData
import com.bakayapps.coctailsbook.data.CoctailPersonalInfo
import com.bakayapps.coctailsbook.viewmodels.BaseViewModel

class CoctailViewModel(private var coctailId: String) :
    BaseViewModel<CoctailState>(CoctailState()) {
    private val repository = CoctailRepository

    init {
        // subscribe on mutable data
        subscribeToDataSource(getCoctailData()) { coctail, state ->
            coctail ?: return@subscribeToDataSource null
            state.copy(
                shareLink = coctail.shareLink,
                title = coctail.title,
                author = coctail.author,
                category = coctail.category,
                categoryIcon = coctail.categoryIcon
                //date = coctail.date.format()
            )
        }

        subscribeToDataSource(getCoctailRecipe()) { recipe, state ->
            recipe ?: return@subscribeToDataSource null
            state.copy(
                isLoadingRecipe = false,
                recipe = recipe
            )
        }

        subscribeToDataSource(getCoctailPersonalInfo()) { info, state ->
            info ?: return@subscribeToDataSource null
            state.copy(
                isBookmark = info.isBookmark,
                isLike = info.isLike
            )
        }

        subscribeToDataSource(repository.getAppSettings()) { settins, state ->
            state.copy(
                isDarkMode = state.isDarkMode
            )
        }
    }

    //Load text from network
    fun getCoctailRecipe(): LiveData<List<Any>?> {
        return repository.loadCoctailRecipe(coctailId)
    }

    //Load text from DB
    fun getCoctailData(): LiveData<CoctailData?> {
        return repository.getCoctail(coctailId)
    }

    //Load data from DB
    fun getCoctailPersonalInfo(): LiveData<CoctailPersonalInfo?> {
        return repository.loadCoctailPersonalInfo(coctailId)
    }
}

data class CoctailState(
    val isAuth: Boolean = false, //пользователь авторизован
    val isLoadingRecipe: Boolean = true, //content загружается
    val isLoadingReviews: Boolean = true, //отзывы загружаются
    val isLike: Boolean = false, //liked
    val isBookmark: Boolean = false, //в закладках
    val isShowMenu: Boolean = false,
    val isBigText: Boolean = false,
    val isDarkMode: Boolean = false, //тёмный режим
    val isSearch: Boolean = false, //режим поиска
    val searchQuery: String? = null, //поисковой запрос
    val searchResults: List<Pair<Int, Int>> = emptyList(), //результаты поиска(стартовая и конечная позиция)
    val searchPosition: Int = 0, //текущая позиция найденного результата
    val shareLink: String? = null, //ссылка Share
    val title: String? = null, //заголовок статьи
    val category: String? = null,
    val categoryIcon: Any? = null, //иконка категории
    val date: String? = null, //дата публикации
    val author: Any? = null,
    val poster: String? = null, //обложка статьи
    val recipe: List<Any> = emptyList(), //рецепт
    val reviews: List<Any> = emptyList() //отзывы
)