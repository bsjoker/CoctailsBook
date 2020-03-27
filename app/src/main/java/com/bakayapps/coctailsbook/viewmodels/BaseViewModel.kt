package com.bakayapps.coctailsbook.viewmodels

import androidx.annotation.UiThread
import androidx.lifecycle.*

abstract class BaseViewModel<T>(initState: T) : ViewModel() {
    val notifications = MutableLiveData<Event<Notify>>()

    /***
     * Инициализация начального состояния аргументом конструктоа, и объявления состояния как
     * MediatorLiveData - медиатор исспользуется для того чтобы учитывать изменяемые данные модели
     * и обновлять состояние ViewModel исходя из полученных данных
     */
    val state : MediatorLiveData<T> = MediatorLiveData<T>().apply {
        value = initState
    }

    /***
     * getter для получения not null значения текущего состояния ViewModel
     */
    val currentState
        get() = state.value!!

    /***
     * лямбда выражение принимает в качестве аргумента текущее состояние и возвращает
     * модифицированное состояние, которое присваивается текущему состоянию
     */
    @UiThread
    protected inline fun updateState(update: (currentState: T) -> T){
        val updateState: T = update(currentState)
        state.value = updateState
    }

    /***
     * функция для создания уведомления пользователя о событии (событие обрабатывается только один раз)
     * соответсвенно при изменении конфигурации и пересоздании Activity уведомление не будет вызвано
     * повторно
     */
    @UiThread
    protected fun notify(content: Notify) {
        notifications.value = Event(content)
    }

    /***
     * более компактная форма записи observe() метода LiveData принимает последним аргумент лямбда
     * выражение обрабатывающее изменение текущего стостояния
     */
    fun observState(owner: LifecycleOwner, onChanged: (newState: T) -> Unit){
        state.observe(owner, Observer{onChanged(it!!)})
    }

    /***
     * более компактная форма записи observe() метода LiveData вызывает лямбда выражение обработчик
     * только в том случае если уведомление не было уже обработанно ранее,
     * реализует данное поведение с помощью EventObserver
     */
    fun observeNotificanions(owner: LifecycleOwner, onNotify: (notification: Notify) -> Unit){
        notifications.observe(owner, EventObserver{onNotify(it)})
    }

    /***
     * функция принимает источник данных и лямбда выражение обрабатывающее поступающие данные источника
     * лямбда принимает новые данные и текущее состояние ViewModel в качестве аргументов,
     * изменяет его и возвращает модифицированное состояние, которое устанавливается как текущее
     */
    protected fun <S> subscribeToDataSource(
        source: LiveData<S>,
        onChanged: (newValue: S, currentState: T) -> T?
    ){
        state.addSource(source){
            state.value =onChanged(it, currentState) ?: return@addSource
        }
    }
}

class ViewModelFactory(private val params: String) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoctailViewModel::class.java)){
            return CoctailViewModel(params) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

class Event<out E>(private val content: E) {
    var hasBeenHandled = false

    /***
     * возвращает контент который еще не был обработан иначе null
     */
    fun getContentIfNotHandled(): E?{
        return if (hasBeenHandled) null
            else {
            hasBeenHandled = true
            content
        }
    }
}

/***
 * в качестве аргумента конструктора принимает лямбда выражение обработчик в аргумент которой передается
 * необработанное ранее событие получаемое в реализации метода Observer`a onChanged
 */
class EventObserver<E>(private val onEventUnhandledContent: (E) -> Unit) :Observer<Event<E>>{
    override fun onChanged(event: Event<E>?) {
        //если есть необработанное событие (контент) передай в качестве аргумента в лямбду
        // onEventUnhandledContent
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

sealed class Notify(val message: String){
    data class TextMessage(val msg: String) : Notify(msg)

    data class ActionMessage(
        val msg: String,
        val actionLabel: String,
        val actionHandler: (() -> Unit)
    ) : Notify(msg)

    data class ErrorMessage(
        val msg: String,
        val errLabel: String,
        val errHandler: (() -> Unit)?
    ) : Notify(msg)
}
