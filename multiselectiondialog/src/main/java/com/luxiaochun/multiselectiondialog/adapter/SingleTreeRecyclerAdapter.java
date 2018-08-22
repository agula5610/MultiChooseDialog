package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 10:10
 * 单选(针对单列无子类的情况，也是最常用的一种情况)
 */
public class SingleTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    public SingleTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand, OnItemClickListener onItemClickListener) {
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
        viewHolder.llContent.setGravity(Gravity.CENTER);
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
