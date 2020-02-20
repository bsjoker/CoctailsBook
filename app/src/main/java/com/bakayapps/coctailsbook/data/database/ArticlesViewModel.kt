package com.bakayapps.coctailsbook.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bakayapps.coctailsbook.base.BaseViewModel
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel
import com.bakayapps.coctailsbook.data.remote.repository.CoctailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel(coctailsRepositoryImpl: CoctailsRepositoryImpl, application: Application) : BaseViewModel() {

    private val repository: ArticlesRepository
    val allArticles: LiveData<List<ArticlesModel>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val articlesDao = AppDatabase.getAppDataBase(
            application
            //,viewModelScope
        ).articlesDao()
        repository = ArticlesRepository(articlesDao)
        allArticles = repository.allArticles
    }

    fun insert(articlesModel: ArticlesModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(articlesModel)
    }
}