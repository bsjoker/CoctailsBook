package com.bakayapps.coctailsbook.database

import androidx.room.TypeConverter
import com.bakayapps.coctailsbook.data.database.entity.RecipesModel
import com.google.gson.Gson
import java.util.*

class ListConverter {
    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<RecipesModel>? {

        if (data == null){
            return Collections.emptyList()
        }
        return gson.fromJson(data, Array<RecipesModel>::class.java).toList()

    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<RecipesModel>?): String? {
        return gson.toJson(someObjects)
    }
}