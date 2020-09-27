package com.nanda.anuraassignment.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanda.anuraassignment.model.PostDao
import com.nanda.anuraassignment.model.PostData
import com.nanda.anuraassignment.network.repository.MainRepository
import com.nanda.anuraassignment.ui.contract.MainContract
import com.nanda.anuraassignment.utils.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repo: MainRepository,
    private val dao: PostDao
) : ViewModel(), MainContract.ViewModel {

    private val postListLiveData: MutableLiveData<ResponseResult<List<PostData>, Exception>> =
        MutableLiveData()
    private val postListLocalLiveData: MutableLiveData<List<PostData>> = MutableLiveData()
    private val loadingStateLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun observePostDataRemote(): MutableLiveData<ResponseResult<List<PostData>, Exception>> =
        postListLiveData

    override fun observePostDataLocal(): LiveData<List<PostData>> = postListLocalLiveData

    override fun observeLoadingVisibility(): MutableLiveData<Boolean> = loadingStateLiveData

    override fun getPostDataRemote() {
        loadingStateLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repo.getPost()) {
                is ResponseResult.Success -> {
                    postListLiveData.postValue(ResponseResult.build { response.value })

                    if (dao.all.isNullOrEmpty()) {
                        dao.insertAll(*response.value.toTypedArray())
                    }
                }

                is ResponseResult.Error -> {
                    postListLiveData.postValue(response)
                }
            }
            loadingStateLiveData.postValue(false)
        }
    }

    override fun getPostDataLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            postListLocalLiveData.postValue(dao.all)
        }
    }
}