package com.codebros.eripple.screen.account.bank

import android.content.Intent
import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivitySelectBankBinding
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.account.join.JoinActivity
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.main.MainActivity
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.bank.BankAdapterListener

class SelectBankActivity2 : BaseActivity<SelectBankViewModel, ActivitySelectBankBinding>(){

    override val viewModel: SelectBankViewModel by viewModels()

    override fun getViewBinding(): ActivitySelectBankBinding =
        ActivitySelectBankBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.bankListLiveData.observe(this@SelectBankActivity2) { result ->
            adapter.submitList(result?.toMutableList())
        }
    }

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(this@SelectBankActivity2)
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<Bank, SelectBankViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            object : BankAdapterListener {
                override fun onItemClick(model: Bank) {
                    setResult(
                        RESULT_OK,
                        Intent(this@SelectBankActivity2, JoinActivity::class.java)
                            .putExtra("model", model)
                    )

                    finish()

                }

            }
        )
    }

    override fun initViews() {
        super.initViews()
        with(binding) {

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            bankRecyclerView.adapter = adapter
            viewModel.getBankList()


        }

    }
}