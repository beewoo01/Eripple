package com.codebros.eripple.screen.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HackyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public HackyTextView(@NonNull Context context) {
        super(context);
    }

    public HackyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HackyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
