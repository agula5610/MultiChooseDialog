package com.luxiaochun.multiselectiondialog.cell;

import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

/**
 * ProjectName: JiuZhou
 * Author: jun
 * Date: 2018-01-10 16:07
 */
public interface Cell {
    /**
     * 回收资源
     */
    void releaseResource();

    /**
     * 获取viewType
     *
     * @return
     */
    int getItemType();

    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     * @return
     */
    void bindViewHolder(RVBaseViewHolder holder, int position);

}
