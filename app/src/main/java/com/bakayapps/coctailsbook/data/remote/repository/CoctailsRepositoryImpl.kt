package com.bakayapps.coctailsbook.data.remote.repository

import android.util.Log
import com.bakayapps.coctailsbook.data.remote.NetworkDataSource
import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.bakayapps.coctailsbook.database.ArticlesDao
import com.bakayapps.coctailsbook.iCoctailsDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoctailsRepositoryImpl(
    //private val networkDataSource: NetworkDataSource
    private val coctailsDBApi: iCoctailsDBApi,
    private val coctailsDAO: ArticlesDao
    ) : CoctailsRepository {

    private var list: List<ShortItemCoctail> = emptyList<ShortItemCoctail>()

//    init {
//        networkDataSource.apply {
//            downloadedShortList.observeForever { newCurrentWeather ->
//                persistFetchedCurrentWeather(newCurrentWeather)
//                list = newCurrentWeather
//            }
//        }
//    }

    override suspend fun getCurrentCategoryCoctail(category: String) =
        coctailsDBApi.getShortListCoctails(category)

//    override suspend fun getCurrentCategoryCoctail(category: String): LiveData<out List<ShortItemCoctail>> {
//        return withContext(Dispatchers.IO) {
//            initWeatherData()
//            return@withContext list
//        }
//    }

//    override suspend fun getFutureWeatherList(
//        startDate: LocalDate,
//        metric: Boolean
//    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
//        return withContext(Dispatchers.IO) {
//            initWeatherData()
//            return@withContext if (metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
//            else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
//        }
//    }
//
//    override suspend fun getFutureWeatherByDate(
//        date: LocalDate,
//        metric: Boolean
//    ): LiveData<out UnitSpecificDetailFutureWeatherEntry> {
//        return withContext(Dispatchers.IO) {
//            initWeatherData()
//            return@withContext if (metric) futureWeatherDao.getDetailedWeatherByDateMetric(date)
//            else futureWeatherDao.getDetailedWeatherByDateImperial(date)
//        }
//    }
//
//    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
//        return withContext(Dispatchers.IO) {
//            return@withContext weatherLocationDao.getLocation()
//        }
//    }

    private fun persistFetchedCurrentWeather(fetchedWeather: List<ShortItemCoctail>) {
        GlobalScope.launch(Dispatchers.IO) {
//            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
//            weatherLocationDao.upsert(fetchedWeather.location)
            Log.d("TAG", fetchedWeather[0].nameCoctail)
        }
    }

//    private fun persistFetchedFutureWeather(fetchedWeather: FutureWeatherResponse) {
//
//        fun deleteOldForecastData() {
//            val today = LocalDate.now()
//            futureWeatherDao.deleteOldEntries(today)
//        }
//
//        GlobalScope.launch(Dispatchers.IO) {
//            deleteOldForecastData()
//            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
//            futureWeatherDao.insert(futureWeatherList)
//            weatherLocationDao.upsert(fetchedWeather.location)
//        }
//    }

//    private suspend fun initWeatherData() {
//        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()
//
//        if (lastWeatherLocation == null
//            || locationProvider.hasLocationChanged(lastWeatherLocation)) {
//            fetchCurrentWeather()
//            fetchFutureWeather()
//            return
//        }
//
//        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
//            fetchCurrentWeather()
//
//        if (isFetchFutureNeeded())
//            fetchFutureWeather()
//    }

//    private suspend fun fetchCurrentWeather() {
//        networkDataSource.fetchShortListCoctails("Cocktail")
//    }
}