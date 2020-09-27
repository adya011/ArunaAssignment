package com.nanda.anuraassignment.ui.controller

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.nanda.anuraassignment.utils.ResponseResult
import com.nanda.anuraassignment.ui.contract.MainContract
import com.nanda.anuraassignment.ui.contract.MainEvent
import java.lang.Exception

class MainController(
    private val lifeCycleOwner: LifecycleOwner,
    private val view: MainContract.View,
    private val viewModel: MainContract.ViewModel
) : MainContract.Controller {

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnStart -> onControllerStart()
        }
    }

    private fun onControllerStart() {
        viewModel.observePostDataRemote().observe(lifeCycleOwner, Observer {
            when (it) {
                is ResponseResult.Success -> {
                    view.updatePostRemote(it.value)
                }
                is ResponseResult.Error -> {
                    onErrorResult(it)
                    viewModel.getPostDataLocal()
                }
            }
        })

        viewModel.observePostDataLocal().observe(lifeCycleOwner, Observer {
            view.updatePostLocal(it)
        })

        viewModel.observeLoadingVisibility().observe(lifeCycleOwner, Observer {
            view.updateLoadingState(it)
        })

        viewModel.getPostDataRemote()
    }

    private fun onErrorResult(errorEvent: ResponseResult.Error<Exception>) {
        view.onError("error: ${errorEvent}")
    }
}