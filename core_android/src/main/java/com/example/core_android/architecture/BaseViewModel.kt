package com.example.core_android.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<State, Actions>(initialState: State) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: Flow<State> = _state

    /**
     * replay = 1 и _action.resetReplayCache()
     * костыль для решения проблемы повторного получения Action при пересоздании Fragment или Activity
     */
    private val _action: MutableSharedFlow<Actions> = MutableSharedFlow(replay = 1)
    val action: Flow<Actions> = _action.onEach { _action.resetReplayCache() }

    /**
     * Получение internalState
     */
    protected fun getState(): State {
        return _state.value
    }

    /**
     * Use copy function to change state properties
     *
     * Example: modifyState { copy(loginError = "Ошибка!") }
     */
    protected fun modifyState(block: State.() -> State) {
        _state.update { state -> block(state) }
    }

    /**
     * Отправка события
     */
    protected fun onAction(action: Actions) {
        _action.tryEmit(action)
    }

    interface Factory<T, V : ViewModel> {
        fun create(state: T): V
    }

}