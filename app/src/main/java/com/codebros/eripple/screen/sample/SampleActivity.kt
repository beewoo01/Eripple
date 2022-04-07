package com.codebros.eripple.screen.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivitySampleBinding
import com.codebros.eripple.model.sample.SamplePhoto
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.sample.SampleListener

class SampleActivity :
    BaseActivity<SampleViewModel, ActivitySampleBinding>() {

    override fun getViewBinding(): ActivitySampleBinding =
        ActivitySampleBinding.inflate(layoutInflater)

    override val viewModel: SampleViewModel by viewModels()

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(this@SampleActivity)
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<SamplePhoto, SampleViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : SampleListener {
                override fun onClickItem(model: SamplePhoto) {
                    Log.wtf("adapter Click title", model.title)
                }

            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.wtf("SampleActivity", "onCreate")
        viewModel.postPhotos()
    }

    override fun initViews() = with(binding){
        Log.wtf("SampleActivity", "initViews")
        sampleRe.adapter = adapter
        /*sampleRe.apply {
            layoutManager = LinearLayoutManager(this@SampleActivity)
            adapter = adapter
        }*/
    }


    override fun observeData() = viewModel.photos.observe(this@SampleActivity) {
        //Update UI
        Log.wtf("SampleActivity", "observeData")
        adapter.submitList(it?.toMutableList())
        //Log.wtf("observeData", it.toString())

    }


}