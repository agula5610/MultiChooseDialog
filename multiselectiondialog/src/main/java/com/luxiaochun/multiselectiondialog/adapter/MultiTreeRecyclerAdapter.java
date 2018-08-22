package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 15:50
 * Copyright: (C)HESC Co.,Ltd. 2016. All rights reserved.
 */
public class MultiTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    public MultiTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, iconExpand, iconNoExpand, null);
    }

    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, final int position) {
        final MyHoder viewHolder = (MyHoder) holder;
        viewHolder.label.setText(node.getName());
        viewHolder.cb.setVisibility(View.VISIBLE);
        if (node.isChecked()) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node, viewHolder.cb.isChecked());
            }
        });
    }
}