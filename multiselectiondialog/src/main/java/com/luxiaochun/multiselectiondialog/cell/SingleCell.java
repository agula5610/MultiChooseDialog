package com.luxiaochun.multiselectiondialog.cell;

import android.os.Handler;
import android.view.Gravity;
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
 * Date: 2018-08-23 10:16
 */
public class SingleCell extends RVBaseCell<Node> {
    private OnItemClickListener onItemClickListener;
    MultiSelectionDialogFragment fragment;
    public SingleCell(MultiSelectionDialogFragment fragment, Node bean, OnItemClickListener onItemClickListener) {
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
        holder.getLinearLayout(R.id.ll_content).setGravity(Gravity.CENTER);
        holder.setText(R.id.id_treenode_label, mData.getName());
        holder.getmItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (fragment != null && onItemClickListener != null) {
                            if (fragment.isShowing()) {
                                fragment.dismiss();
                            }
                            onItemClickListener.onClick(mData, position);
                        }
                    }
                }, 60);
            }
        });
    }
}