package com.nanda.anuraassignment.ui.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nanda.anuraassignment.model.PostData
import com.nanda.anuraassignment.utils.ResponseResult
import java.lang.Exception

interface MainContract {
    interface View {
        fun updatePostRemote(postList: List<PostData>)
        fun updatePostLocal(postList: List<PostData>)
        fun onError(message: String)
        fun updateLoadingState(isLoading: Boolean)
    }

    interface Controller {
        fun handleEvent(event: MainEvent)
    }

    interface ViewModel {
        fun observePostDataRemote(): LiveData<ResponseResult<List<PostData>, Exception>>
        fun observePostDataLocal(): LiveData<List<PostData>>
        fun observeLoadingVisibility(): MutableLiveData<Boolean>
        fun getPostDataRemote()
        fun getPostDataLocal()
    }
}

sealed class MainEvent {
    object OnStart : MainEvent()
}