package com.example.android_1080.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 1.只监听变化，截取多余字数
 * 2.改变右下角字数
 */
public class TextWatcher2 implements TextWatcher {
    private EditText mEditText;
    private TextView mTextView;
    private int maxLen;

    public TextWatcher2(EditText editText, TextView textView, int maxLen) {
        mEditText = editText;
        mTextView = textView;
        this.maxLen = maxLen;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextView.setText(String.valueOf(EditTextUtils.limitInput(mEditText, maxLen)));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
