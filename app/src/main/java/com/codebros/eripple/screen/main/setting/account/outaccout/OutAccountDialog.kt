package com.codebros.eripple.screen.main.setting.account.outaccout

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.codebros.eripple.R
import com.codebros.eripple.databinding.DialogOutAccountBinding

class OutAccountDialog(
    context: Context, private val callBack: (Boolean) -> Unit
) : Dialog(context, R.style.FullScreenDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_out_account)

        window?.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.AnimationPopupStyle
        }

        findViewById<TextView>(R.id.cancel_txv).setOnClickListener {
            callBack(false)
            dismiss()
        }

        findViewById<TextView>(R.id.ok_txv).setOnClickListener {
            callBack(true)
            dismiss()
        }
    }

}