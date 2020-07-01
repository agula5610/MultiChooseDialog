package com.luxiaochun.multiselectiondialog.adapter;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;

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
    private int themeColor;
    private Node checkedNode;  //记录上一次选中的项目

    public SingleAdapter(List<Node> datas, int themeColor) {
        super(datas);
        this.themeColor = themeColor;
        for (Node node : datas) {
            if (node.isChecked()) {
                checkedNode = node;
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.luxiaochun_single_selection_item;
    }

    @Override
    public void onBindViewHolder(final Node node, RVBaseViewHolder holder, final int position) {
        holder.setText(R.id.id_treenode_label, node.getName());
        // 动态设置radiobutton的选中颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RadioButton rb = holder.getRadioButton(R.id.single_radio_btn);
            int uncheckedColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.gray);
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
            rb.setButtonTintList(colorStateList);
        }
        if (node.isChecked()) {
            holder.getRadioButton(R.id.single_radio_btn).setChecked(true);
        } else {
            holder.getRadioButton(R.id.single_radio_btn).setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioChecked(node, checkedNode);
                checkedNode = node;
            }
        });
        holder.getRadioButton(R.id.single_radio_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioChecked(node, checkedNode);
                checkedNode = node;
            }
        });
    }
}
