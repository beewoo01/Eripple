package com.codebros.eripple.screen.custom

import android.content.Context
import android.text.Selection
import android.text.Spannable
import android.util.AttributeSet
import android.view.MotionEvent


class HackyTextView2 : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val startSelection = selectionStart
        val endSelection = selectionEnd
        if (startSelection < 0 || endSelection < 0) {
            Selection.setSelection(text as Spannable, text.length)
        } else if (startSelection != endSelection) {
            if (event!!.actionMasked === MotionEvent.ACTION_DOWN) {
                val text = text
                setText(null)
                setText(text)
            }
        }
        return super.dispatchTouchEvent(event)
    }


}