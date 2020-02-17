package com.bakayapps.coctailsbook.database

import androidx.lifecycle.LiveData
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel

class ArticlesRepository(private val articlesDao: ArticlesDao) {
    val allArticles: LiveData<List<ArticlesModel>> = articlesDao.getAll()

    suspend fun insert(articlesModel: ArticlesModel) {
        articlesDao.insert(articlesModel)
    }
}