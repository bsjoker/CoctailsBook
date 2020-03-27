package com.bakayapps.coctailsbook.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.R
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

object LocalDataHolder{
    val coctailData = MutableLiveData<CoctailData>(null)
    val coctailInfo = MutableLiveData<CoctailPersonalInfo>(null)
    val settings = MutableLiveData(AppSettings())

    fun findCoctail(coctailId : String): LiveData<CoctailData?>{

        coctailData.value = CoctailData(
            title = "CoordinatorLayout Basic",
            category = "Android",
            categoryIcon = R.drawable.logo,
            date = Date(),
            author = "Skill-Branch"
        )

        return coctailData
    }

    fun findCoctailPersonalInfo(coctailId: String): LiveData<CoctailPersonalInfo?>{
        coctailInfo.value = CoctailPersonalInfo(false, false)
        return coctailInfo
    }

    fun getAppSettings() = settings
    fun updateAppSettings(appSettings: AppSettings){
        settings.value = appSettings
    }

    fun updateCoctailPersonalInfo(info: CoctailPersonalInfo){
        coctailInfo.value = info
    }
}

object NetworkDataholder{
    val recipe = MutableLiveData<List<Any>?>(null)
    val listShortItemByCategoriesCoctails = MutableLiveData<List<ShortItemCoctail>?>(null)
    var disposable: Disposable? = null

    fun loadCoctailRecipe(coctailId: String): LiveData<List<Any>?>{
        recipe.value = listOf(longText)
        return recipe
    }

    private val mICoctailApi by lazy {
        App.createCoctailApi()
    }

    fun loadCategoryCoctailsFromNetwork(category: String): LiveData<List<ShortItemCoctail>?>{
        disposable = mICoctailApi.getShortListCoctails(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    listShortItemByCategoriesCoctails.value = result.drinks
                    Log.d("DataHolder", "First recipe: ${result.drinks[0].nameCoctail}")
                },
                { error -> Log.d("DataHolder", "Error to try catch data! $error") }
            )

        return listShortItemByCategoriesCoctails
    }
}

data class CoctailData(
    val shareLink: String? = null,
    val title: String? = null,
    val category: String? = null,
    val categoryIcon: Any? = null,
    val date: Date,
    val author: Any? = null,
    val poster: String? = null,
    val content: List<Any> = emptyList()
)

data class ShortItemCoctail (
    @SerializedName("strDrink") val nameCoctail: String,
    @SerializedName("strDrinkThumb") val thumbnail: String,
    @SerializedName("idDrink") val id: String
)

data class CoctailPersonalInfo(
    val isLike: Boolean = false,
    val isBookmark: Boolean = false
)

data class AppSettings(
    val isDarkMode: Boolean = false
)

val longText: String = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nibh sapien, consectetur et ultrices quis, convallis sit amet augue. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum et convallis augue, eu hendrerit diam. Curabitur ut dolor at justo suscipit commodo. Curabitur consectetur, massa sed sodales sollicitudin, orci augue maximus lacus, ut elementum risus lorem nec tellus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent accumsan tempor lorem, quis pulvinar justo. Vivamus euismod risus ac arcu pharetra fringilla.

Maecenas cursus vehicula erat, in eleifend diam blandit vitae. In hac habitasse platea dictumst. Duis egestas augue lectus, et vulputate diam iaculis id. Aenean vestibulum nibh vitae mi luctus tincidunt. Fusce iaculis molestie eros, ac efficitur odio cursus ac. In at orci eget eros dapibus pretium congue sed odio. Maecenas facilisis, dolor eget mollis gravida, nisi justo mattis odio, ac congue arcu risus sed turpis.

Sed tempor a nibh at maximus. Nam ultrices diam ac lorem auctor interdum. Aliquam rhoncus odio quis dui congue interdum non maximus odio. Phasellus ut orci commodo tellus faucibus efficitur. Nulla congue nunc vel faucibus varius. Sed cursus ut odio ut fermentum. Vivamus mattis vel velit et maximus. Proin in sapien pharetra, ornare metus nec, dictum mauris.

Praesent nisl nisl, iaculis id nulla in, congue eleifend leo. Sed aliquet elementum massa et gravida. Nulla facilisi. Cras convallis vestibulum elit et sodales. Cras consequat eleifend metus non tempus. Vivamus venenatis consequat mollis. Nunc suscipit ipsum at nunc dignissim porta. Sed accumsan tellus non mauris fermentum pulvinar. Morbi felis est, accumsan id est id, dictum interdum nulla. Proin ultrices, ante at placerat venenatis, tortor enim ullamcorper magna, at finibus nisi dui in ante. Vivamus convallis velit tortor, at mattis diam rhoncus vel. Integer at placerat turpis, vel laoreet nibh. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus fermentum tellus malesuada diam facilisis gravida. Quisque a semper ex, at semper dolor.

In a turpis suscipit, venenatis arcu id, condimentum nulla. Mauris id felis id metus aliquet facilisis ut sit amet lectus. In aliquam dapibus mollis. Morbi sollicitudin purus ultricies dictum feugiat. Morbi lobortis mollis faucibus. Nunc mattis nec est sagittis semper. Curabitur in dignissim elit.
""".trimIndent()