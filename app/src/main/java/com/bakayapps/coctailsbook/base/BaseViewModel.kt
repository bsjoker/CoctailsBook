package com.bakayapps.coctailsbook.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {
    /**
     * Coroutines in a Main Thread
     */
    protected val mainScope = CoroutineScope(Dispatchers.Main)

    /**
     * Coroutines in a Pool of Thread
     */
    protected val ioScope = CoroutineScope(Dispatchers.Default)

    override fun onCleared() {
        super.onCleared()
        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}