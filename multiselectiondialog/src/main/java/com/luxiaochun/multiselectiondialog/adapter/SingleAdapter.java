package com.luxiaochun.multiselectiondialog.adapter;

import android.view.View;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 10:10
 * 单选(针对单列无子类的情况，也是最常用的一种情况)
 */
public class SingleAdapter extends AbsTreeRecyclerAdapter {

    public SingleAdapter(List<Node> datas) {
        super(datas);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.single_selection_item;
    }

    @Override
    public void onBindViewHolder(final Node node, RVBaseViewHolder holder, final int position) {
        holder.setText(R.id.id_treenode_label, node.getName());
        if (node.isChecked()) {
            holder.getRadioButton(R.id.single_radio_btn).setChecked(true);
        } else {
            holder.getRadioButton(R.id.single_radio_btn).setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioChecked(node);
            }
        });
        holder.getRadioButton(R.id.single_radio_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioChecked(node);
            }
        });
    }
}
