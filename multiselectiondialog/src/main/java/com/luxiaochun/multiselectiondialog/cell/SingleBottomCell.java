package com.luxiaochun.multiselectiondialog.cell;

import android.os.Handler;
import android.util.TypedValue;
import android.view.View;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.fragment.MultiSelectionDialogFragment;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog.cell
 * Author: jun
 * Date: 2018-08-23 15:45
 */
public class SingleBottomCell extends RVBaseCell<Node> {
    private OnItemClickListener onItemClickListener;
    MultiSelectionDialogFragment fragment;

    public SingleBottomCell(MultiSelectionDialogFragment fragment, Node bean, OnItemClickListener onItemClickListener) {
        super(bean);
        this.fragment = fragment;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return R.layout.multi_selection_item;
    }

    @Override
    public void bindViewHolder(final RVBaseViewHolder holder, final int position) {
        // 设置内边距
        holder.itemView.setPadding(mData.getLevel() * 30, 3, 3, 3);
        holder.setText(R.id.id_treenode_label, mData.getName());
        if (mData.getIcon() == -1) {
            holder.getImageView(R.id.icon).setVisibility(View.INVISIBLE);
        } else {
            holder.getImageView(R.id.icon).setVisibility(View.VISIBLE);
            holder.getImageView(R.id.icon).setImageResource(mData.getIcon());
        }
        if (mData.isRoot() && position == 0) {
            Node.textSize = holder.getTextView(R.id.id_treenode_label).getTextSize();
        }
        if (Node.textSize > 0) {
            holder.getTextView(R.id.id_treenode_label).setTextSize(TypedValue.COMPLEX_UNIT_PX, Node.textSize - 4 * mData.getLevel());
        }
        holder.getmItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (fragment != null && onItemClickListener != null) {
                            if (fragment.isShowing() && mData.isLeaf()) {
                                fragment.dismiss();
                            }
                            fragment.getmAdapter().expandOrCollapse(position);
                            onItemClickListener.onClick(mData, position);
                        }
                    }
                }, 60);
            }
        });
    }
}