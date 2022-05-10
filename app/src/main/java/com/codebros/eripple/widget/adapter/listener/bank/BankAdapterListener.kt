package com.codebros.eripple.widget.adapter.listener.bank

import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface BankAdapterListener : AdapterListener{
    fun onItemClick(model : Bank)
}