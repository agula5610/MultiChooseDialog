package com.luxiaochun.multiselectiondialog.cell;

/**
 * ProjectName: JiuZhou
 * Author: jun
 * Date: 2018-01-10 16:12
 */
public abstract class RVBaseCell<T> implements Cell {
    public T mData;

    public RVBaseCell(T t) {
        mData = t;
    }
}