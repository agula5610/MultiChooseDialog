package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 15:40
 * Copyright: (C)HESC Co.,Ltd. 2016. All rights reserved.
 */
public class SingleAllTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    public SingleAllTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand, OnItemClickListener onItemClickListener) {
        super(mTree, context, datas, iconExpand, iconNoExpand, onItemClickListener);
    }

    @Override
    public List<Node> getCheckedNodeList() {
        return null;
    }

    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, final int position) {
        final MyHoder viewHolder = (MyHoder) holder;
        viewHolder.label.setText(node.getName());
        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        float textSize = Node.textSize;
        if (textSize > 0) {
            viewHolder.label.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize - 4 * node.getLevel());
        }

        viewHolder.llIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
            }
        });

        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClick(node, position);
                        }
                    }
                }, 60);
            }
        });
    }
}
