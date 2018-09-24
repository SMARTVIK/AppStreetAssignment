package com.hunter.appstreetassignment.detail;

import android.view.View;

public interface OnItemClick<T> {
    void onItemClick(T t, int pos, View view);
}
