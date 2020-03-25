package com.bakayapps.coctailsbook.mvp.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel
import com.bakayapps.coctailsbook.database.CoctailViewModel
import com.bakayapps.coctailsbook.di.LoadingContract

class LoadingActivity : AppCompatActivity(), LoadingContract.View {
    companion object {
        const val TAG = "LoadingActivity"
    }

    //val mPresenter: LoadingContract.Presenter by inject { parametersOf(this) }

    private lateinit var articlesViewModel: CoctailViewModel

    private var isStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        //mPresenter.getRecipesFromServer()

        showProgress()

//        articlesViewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    override fun setArtictesViewModel(am: ArticlesModel) {
        //articlesViewModel.insert(am)
    }

    private fun showProgress() {
        var speed = 105
        Thread(Runnable {
            var prs = 0

            while (prs < 100) {

                speed--
                Thread.sleep(speed.toLong())
                pb_horizontal.setProgress(prs)

                prs++
            }
            if (!isStart) {
                startActivity(Intent(this@LoadingActivity, MainActivity::class.java))
                isStart = true
            }
        }).start()
    }

    override fun startNewActivity(value: Boolean) {
        when (value) {
            //true -> startActivity(Intent(this@LoadingActivity, MyWebView::class.java))
            false -> startActivity(Intent(this@LoadingActivity, MainActivity::class.java))
        }
        isStart = true
    }

    override fun changeIcon(name: String) {
        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        if (prefs.getString("alias-name", "") != name) {
            val pm = packageManager
            try {
                pm.setComponentEnabledSetting(
                    ComponentName(App.instance.applicationContext, "$packageName.$name"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
                pm.setComponentEnabledSetting(
                    componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            } catch (e: Exception) {
                e.printStackTrace()

            }
            prefs.edit().putString("alias-name", name).apply()
        }
    }

    override fun onPause() {
        super.onPause()
        //mPresenter.disposeDisposable()
    }
}