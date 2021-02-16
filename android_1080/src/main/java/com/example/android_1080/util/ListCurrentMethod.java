package com.example.android_1080.util;


import com.gfq.refreshview.RVBindingAdapter;
import com.gfq.refreshview.RefreshView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * created Dengsixue
 * Date 2021/1/16 17:15
 * 刷新库公共方法
 */
public class ListCurrentMethod {
    public static <T> void noAnimRequestRefresh(List<T> data, RVBindingAdapter<T> adapter, RefreshView<T> refreshView) {
        if (data != null && data.size() > 0) {
            adapter.refresh(data);
            refreshView.removeNoDataView();
        } else {
            adapter.clear();
            refreshView.showNoDataView();
        }
    }
    public static <T> void requestRefresh(List<T> data, RVBindingAdapter<T> adapter, RefreshView<T> refreshView, RefreshLayout layout) {
        if (data != null && data.size() > 0){
            adapter.refresh(data);
            refreshView.removeNoDataView();
        }else {
            adapter.clear();
            refreshView.showNoDataView();
        }
        layout.finishRefresh();
    }
    public static <T> void requestLoadMore(List<T> data, RVBindingAdapter<T> adapter, RefreshView<T> refreshView, RefreshLayout layout) {
        if (data != null && data.size() > 0){
            adapter.loadMore(data);
        }else {
            layout.finishLoadMoreWithNoMoreData();
        }
        layout.finishLoadMore();
    }
}
