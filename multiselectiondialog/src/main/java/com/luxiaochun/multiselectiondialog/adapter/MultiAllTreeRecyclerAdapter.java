package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView
 * Author: jun
 * Date: 2018-03-14 16:59
 */
public class MultiAllTreeRecyclerAdapter extends CustomAbsTreeRecyclerAdapter {
    public MultiAllTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, iconExpand, iconNoExpand, null);
    }

    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, final int position) {
        final MyHoder viewHolder = (MyHoder) holder;
        viewHolder.label.setText(node.getName());
        viewHolder.cb.setVisibility(View.VISIBLE);
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
        if (node.isChecked()) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node, viewHolder.cb.isChecked());
            }
        });

        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
            }
        });
    }
}
