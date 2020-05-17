package com.luxiaochun.multiselectiondialog.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.List;

/**
 * Created by jun on 2017-12-21.
 */
public abstract class AbsTreeRecyclerAdapter extends TreeRecyclerAdapter {

    public AbsTreeRecyclerAdapter(List<Node> datas, int iconExpand, int iconNoExpand) {
        super(datas, iconExpand, iconNoExpand);
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }
}
