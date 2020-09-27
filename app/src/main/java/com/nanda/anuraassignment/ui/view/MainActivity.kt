package com.nanda.anuraassignment.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nanda.anuraassignment.R
import com.nanda.anuraassignment.model.PostData
import com.nanda.anuraassignment.ui.contract.MainContract
import com.nanda.anuraassignment.ui.contract.MainEvent
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainContract.View {
    @Inject
    lateinit var controller: MainContract.Controller
    private val mainAdapter: MainAdapter by lazy { MainAdapter(listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        controller.handleEvent(MainEvent.OnStart)
    }

    private fun setupAdapter() {
        rvMain.adapter = mainAdapter
    }

    override fun updatePostRemote(postList: List<PostData>) {
        mainAdapter.setData(postList)
    }

    override fun updatePostLocal(postList: List<PostData>) {
        mainAdapter.setData(postList)
    }

    override fun updateLoadingState(isLoading: Boolean) {
        if (isLoading) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun onError(message: String) {
        Toast.makeText(applicationContext, "error: ${message}", Toast.LENGTH_SHORT).show()
    }
}