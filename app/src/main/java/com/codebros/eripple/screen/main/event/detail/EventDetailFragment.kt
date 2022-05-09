package com.codebros.eripple.screen.main.event.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.codebros.eripple.R
import com.codebros.eripple.data.url.DefaultUrl.SAMPLE_IMAGE_URL
import com.codebros.eripple.databinding.FragmentEventDetailBinding
import com.codebros.eripple.extention.load
import com.codebros.eripple.screen.main.setting.notice.detail.NoticeDetailFragmentArgs


class EventDetailFragment : Fragment() {

    private val binding by lazy {
        FragmentEventDetailBinding.inflate(layoutInflater)
    }

    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val model = args.model
        titleTxv.text = model.event_title
        thumbnailImv.load(SAMPLE_IMAGE_URL + model.event_image_url, 20F)

    }


}