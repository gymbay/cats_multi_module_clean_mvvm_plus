package com.example.core_android.architecture

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<State, Actions>(initialState: State) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: Flow<State> = _state

    private val _stateLog by lazy { mutableListOf<State>() }

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
        if (testMode) {
            _stateLog.add(_state.updateAndGet(block))
        } else {
            _state.update(block)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getStateLog(): List<State> {
        if (!testMode) throw IllegalStateException("Invoke turnOnTestMode() before!")
        return _stateLog
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun clearStateLog() {
        if (!testMode) throw IllegalStateException("Invoke turnOnTestMode() before!")
        _stateLog.clear()
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

    companion object {

        private var testMode = false

        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        fun turnOnTestMode() {
            testMode = true
        }

    }

}