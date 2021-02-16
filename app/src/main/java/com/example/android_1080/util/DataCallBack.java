package com.example.android_1080.util;

import java.util.List;

/**
 * created Dengsixue
 * Date 2021/1/16 18:03
 * 获取列表的接口
 */
public interface DataCallBack<T> {
    void onCallBack(List<T> data);
}
