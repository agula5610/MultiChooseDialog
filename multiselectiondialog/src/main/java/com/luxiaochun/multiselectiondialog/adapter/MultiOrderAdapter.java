package com.luxiaochun.multiselectiondialog.adapter;

import android.view.View;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-15 09:01
 */
public class MultiOrderAdapter extends AbsTreeRecyclerAdapter {
    private int limited;

    public MultiOrderAdapter(List<Node> datas, int limited) {
        super(datas);
        this.limited = limited;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.multi_selection_order_item;
    }

    @Override
    public void onBindViewHolder(final Node node, final RVBaseViewHolder holder, final int position) {

        holder.setText(R.id.id_treenode_label, node.getName());
        TextView tv = holder.getTextView(R.id.id_treenode_order);
        tv.setText(node.getLevel() == 0 ? "" : "" + node.getLevel());

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
                if (isCheck) {
                    for (Node nodecycle : mAllNodes) {
                        if (nodecycle.isChecked()) {
                            if (nodecycle.getLevel() > node.getLevel()) {
                                nodecycle.setLevel(nodecycle.getLevel() - 1);
                            } else if (nodecycle.getLevel() == node.getLevel()){
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
}
