package com.luxiaochun.multiselectiondialog.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 15:50
 * Copyright: (C)HESC Co.,Ltd. 2016. All rights reserved.
 */
public class MultiAdapter extends AbsTreeRecyclerAdapter {
    public MultiAdapter(List<Node> datas) {
        super(datas);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.multi_selection_item;
    }

    @Override
    public void onBindViewHolder(final Node node, final RVBaseViewHolder holder, final int position) {
        holder.setText(R.id.id_treenode_label, node.getName());
        final AppCompatCheckBox checkBox = holder.getCheckBox(R.id.cb_select_tree);
        if (node.isChecked()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node, checkBox.isChecked());
            }
        });
    }
}