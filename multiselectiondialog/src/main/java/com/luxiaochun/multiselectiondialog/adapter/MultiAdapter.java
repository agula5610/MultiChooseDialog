package com.luxiaochun.multiselectiondialog.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.content.ContextCompat;
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
    private int themeColor;
    public MultiAdapter(List<Node> datas, int themeColor) {
        super(datas);
        this.themeColor = themeColor;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.luxiaochun_multi_selection_item;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(final Node node, final RVBaseViewHolder holder, final int position) {
        holder.setText(R.id.id_treenode_label, node.getName());
        final AppCompatCheckBox checkBox = holder.getCheckBox(R.id.cb_select_tree);
        // 动态设置AppCompatCheckBox的选中颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int uncheckedColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.luxiaochun_gray);
            int checkedColor = ContextCompat.getColor(holder.itemView.getContext(), themeColor);
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_checked}, //未选中
                            new int[]{android.R.attr.state_checked} //选中
                    },
                    new int[]{
                            uncheckedColor,//未选中
                            checkedColor //选中

                    }
            );
            checkBox.setSupportButtonTintList(colorStateList);
        }
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