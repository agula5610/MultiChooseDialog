package com.luxiaochun.multiselectiondialog.adapter;

import android.os.Handler;
import android.util.TypedValue;
import android.view.View;

import com.luxiaochun.multiselectiondialog.MultiSelectionDialogFragment;
import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.List;

/**
 * ProjectName: JiuZhou
 * PackageName: com.example.jun.jiuzhou.MultiTreeListView.adapter
 * Author: jun
 * Date: 2018-03-14 10:21
 * 单选（针对选择叶子界点，可能包含子类）
 */
public class SingleBottomAdapter extends AbsTreeRecyclerAdapter {
    private MultiSelectionDialogFragment fragment;

    public SingleBottomAdapter(MultiSelectionDialogFragment fragment, List<Node> datas, int iconExpand, int iconNoExpand, OnItemClickListener onItemClickListener) {
        super(datas, iconExpand, iconNoExpand, onItemClickListener);
        this.fragment = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.multi_selection_item;
    }

    @Override
    public void onBindViewHolder(final Node node, RVBaseViewHolder holder, final int position) {
        holder.setText(R.id.id_treenode_label, node.getName());
        if (node.getIcon() == -1) {
            holder.getImageView(R.id.tree_img).setVisibility(View.INVISIBLE);
        } else {
            holder.getImageView(R.id.tree_img).setVisibility(View.VISIBLE);
            holder.getImageView(R.id.tree_img).setImageResource(node.getIcon());
        }
        if (position == 0) {
            Node.textSize = holder.getTextView(R.id.id_treenode_label).getTextSize();
        }

        if (Node.textSize > 0) {
            holder.getTextView(R.id.id_treenode_label).setTextSize(TypedValue.COMPLEX_UNIT_PX, Node.textSize - 4 * node.getLevel());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        expandOrCollapse(position);
                        if (onItemClickListener != null) {
                            if (fragment.isShowing() && node.isLeaf()) {
                                fragment.dismiss();
                                onItemClickListener.onClick(node, position);
                            }
                        }
                    }
                }, 60);
            }
        });
    }

}