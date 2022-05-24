package com.codebros.eripple.screen.main.my_point

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentMyPointBinding
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.my_point.currentpoint.CurrentPointFragment
import com.codebros.eripple.screen.main.my_point.exchange.ExchangeHistoryFragment

class MyPointFragment : Fragment() {

    private var binding: FragmentMyPointBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyPointBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFragment(CurrentPointFragment.newInstance(), CurrentPointFragment.TAG)

        initViews()

    }

    private fun initViews() {
        binding?.apply {

            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }

            currentPointTxv.setOnClickListener {
                currentPointTxv.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.subTextColor
                    )
                )
                exchangeTxv.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.defaultGrayTextColor
                    )
                )
                currentPointTxv.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.applesdgothicneob)
                exchangeTxv.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.applesdgothicneom)
                showFragment(CurrentPointFragment.newInstance(), CurrentPointFragment.TAG)
            }

            exchangeTxv.setOnClickListener {

                exchangeTxv.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.subTextColor
                    )
                )

                currentPointTxv.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.defaultGrayTextColor
                    )
                )

                currentPointTxv.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.applesdgothicneom)
                exchangeTxv.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.applesdgothicneob)

                showFragment(ExchangeHistoryFragment.newInstance(), ExchangeHistoryFragment.TAG)
            }

            fabBtn.setOnClickListener {
                findNavController().navigate(R.id.action_my_point_to_exchange_apply)
            }

        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {

        val findFragment = childFragmentManager.findFragmentByTag(tag)


        childFragmentManager.fragments.forEach { fm ->
            childFragmentManager.beginTransaction().hide(fm).commit()
        }

        findFragment?.let {

            childFragmentManager.beginTransaction().show(it).commit()

        } ?: kotlin.run {

            binding?.fragmentContainer?.id?.let {
                childFragmentManager.beginTransaction()
                    .add(it, fragment, tag)
                    .commitAllowingStateLoss()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}