package com.luxiaochun.multiselectiondialog.adapter;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-15 09:01
 */
public class MultiOrderAdapter extends AbsTreeRecyclerAdapter {
    private int limited;
    private int themeColor;

    public MultiOrderAdapter(List<Node> datas, int limited, int themeColor) {
        super(datas);
        this.limited = limited;
        this.themeColor = themeColor;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.luxiaochun_multi_selection_order_item;
    }

    @Override
    public void onBindViewHolder(final Node node, final RVBaseViewHolder holder, final int position) {

        holder.setText(R.id.id_treenode_label, node.getName());
        TextView tv = holder.getTextView(R.id.id_treenode_order);
        tv.setText(node.getLevel() == 0 ? "" : "" + node.getLevel());
        tv.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), themeColor));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (Node node : mAllNodes) {
                    if (node.isChecked()) {
                        ++count;
                    }
                }
                boolean isCheck = node.isChecked();
                int currentLevel = node.getLevel();
                if (isCheck) {
                    for (Node nodecycle : mAllNodes) {
                        if (nodecycle.isChecked()) {
                            if (nodecycle.getLevel() > currentLevel) {
                                nodecycle.setLevel(nodecycle.getLevel() - 1);
                            } else if (nodecycle.getLevel() == currentLevel) {
                                nodecycle.setLevel(0);
                                node.setChecked(false);
                            }
                        }
                    }
                } else {
                    node.setChecked(true);
                    if (count >= limited) {
                        return;
                    }
                    node.setLevel(count + 1);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public List<Node> getCheckedNodeList() {
        List<Node> list = new ArrayList<>();
        for (Node node : mAllNodes) {
            if (node.isChecked() && node.isLeaf()) {
                list.add(node);
            }
        }
        List<Node> orderList = new ArrayList<>();
        for (int i = 1; i < list.size() + 1; i++) {
            for (Node node : list) {
                if (node.getLevel() == i) {
                    orderList.add(node);
                    break;
                }
            }
        }
        return orderList;
    }
}
