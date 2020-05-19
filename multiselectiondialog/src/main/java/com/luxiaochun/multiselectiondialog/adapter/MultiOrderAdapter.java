package com.luxiaochun.multiselectiondialog.adapter;

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
    private List<Node> list = new ArrayList<>();
    private int limited;

    public MultiOrderAdapter(List<Node> datas, int limited) {
        super(datas);
        this.limited = limited;
        list.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.multi_selection_order_item;
    }

    @Override
    public void onBindViewHolder(final Node node, final RVBaseViewHolder holder, final int position) {

        holder.setText(R.id.id_treenode_label, node.getName());
        TextView tv = holder.getTextView(R.id.id_treenode_order);
        if (list.size() < limited) {
            for (int i = 0; i < list.size(); i++) {
                if (node.getId().equals(list.get(i).getId())) {
                    if (i == 0) {
                        tv.setText(R.string.quan1);
                    } else if (i == 1) {
                        tv.setText(R.string.quan2);
                    } else if (i == 2) {
                        tv.setText(R.string.quan3);
                    } else if (i == 3) {
                        tv.setText(R.string.quan4);
                    } else if (i == 4) {
                        tv.setText(R.string.quan5);
                    } else if (i == 5) {
                        tv.setText(R.string.quan6);
                    } else if (i == 6) {
                        tv.setText(R.string.quan7);
                    } else if (i == 7) {
                        tv.setText(R.string.quan8);
                    } else if (i == 8) {
                        tv.setText(R.string.quan9);
                    }
                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                node.setChecked(!node.isChecked());
                if (list.size() < limited) {
                    if (node.isChecked()) {
                        list.add(node);
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId().equals(node.getId())) {
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
