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
 * Date: 2018-03-14 10:21
 * 单选（针对选择叶子界点，可能包含子类）
 */
public class SingleBottomTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    public SingleBottomTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand, OnItemClickListener onItemClickListener) {
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
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        expandOrCollapse(position);
                        if (onItemClickListener != null && node.isLeaf()) {
                            onItemClickListener.onClick(node, position);
                        }
                    }
                }, 60);
            }
        });
    }
}