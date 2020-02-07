package com.bakayapps.coctailsbook.models

import androidx.room.*
import com.bakayapps.coctailsbook.database.ListConverter

@Entity(tableName = "articlesData")
data class ArticlesModel(
    @PrimaryKey(autoGenerate = true) var id: Long? = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @TypeConverters(ListConverter::class)
    val articles: List<RecipesModel>
)