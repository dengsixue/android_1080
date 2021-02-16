package com.example.android_1080.util;

import android.text.Editable;
import android.text.Selection;
import android.widget.EditText;

public class EditTextUtils {
    /**
     * 限制edittext的输入数目，并进行截断
     * @param editText
     * @param maxLen
     * @return
     */
    public static int limitInput(EditText editText, int maxLen) {
        Editable editable = editText.getText();
        int len = editable.length();

        if (len > maxLen) {
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, maxLen);
            editText.setText(newStr);
            editable = editText.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
            return maxLen;
        }
        return len;
    }
}
