package com.codebros.eripple.util.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class NpaLinearLayoutManager : LinearLayoutManager {

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }

    constructor(context : Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    constructor(context : Context) : super(context)

}