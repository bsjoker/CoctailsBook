package com.bakayapps.coctailsbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel
import com.bakayapps.coctailsbook.database.AppDatabase.Companion.DB_VERSION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ArticlesModel::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao

    companion object {
        const val DB_VERSION = 1
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "myDB"
                )
                    .addMigrations(MIGRATION_1_TO_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}