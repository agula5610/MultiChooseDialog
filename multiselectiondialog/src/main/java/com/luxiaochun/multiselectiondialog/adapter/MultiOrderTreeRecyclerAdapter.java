package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-15 09:01
 */
public class MultiOrderTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    private List<Node> list = new ArrayList<Node>();
    private int limited = 9;

    public MultiOrderTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand, int limited) {
        super(mTree, context, datas, iconExpand, iconNoExpand, null);
        list.clear();
        this.limited = limited;
    }

    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, final int position) {
        final MyHoder viewHolder = (MyHoder) holder;
        viewHolder.order.setVisibility(View.VISIBLE);
        viewHolder.order.setText("");
        viewHolder.label.setText(node.getName());
        viewHolder.cb.setVisibility(View.VISIBLE);
        if (node.isChecked()) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        if (list.size() < limited) {
            for (int i = 0; i < list.size(); i++) {
                if (node.getName().equals(list.get(i).getName())) {
                    if (i == 0) {
                        viewHolder.order.setText(R.string.quan1);
                    } else if (i == 1) {
                        viewHolder.order.setText(R.string.quan2);
                    } else if (i == 2) {
                        viewHolder.order.setText(R.string.quan3);
                    } else if (i == 3) {
                        viewHolder.order.setText(R.string.quan4);
                    } else if (i == 4) {
                        viewHolder.order.setText(R.string.quan5);
                    } else if (i == 5) {
                        viewHolder.order.setText(R.string.quan6);
                    } else if (i == 6) {
                        viewHolder.order.setText(R.string.quan7);
                    } else if (i == 7) {
                        viewHolder.order.setText(R.string.quan8);
                    } else if (i == 8) {
                        viewHolder.order.setText(R.string.quan9);
                    }
                }
            }
        }

        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node, viewHolder.cb.isChecked());
                if (list.size() < limited) {
                    if (viewHolder.cb.isChecked()) {
                        list.add(node);
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getName().equals(node.getName())) {
                                list.remove(i);
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public List<Node> getCheckedNodeList() {
        return list;
    }
}
