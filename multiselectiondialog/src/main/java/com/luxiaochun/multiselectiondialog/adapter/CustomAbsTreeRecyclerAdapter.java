package com.luxiaochun.multiselectiondialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;
import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.base.TreeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017-12-21.
 */
public abstract class CustomAbsTreeRecyclerAdapter extends TreeRecyclerAdapter {
    protected OnItemClickListener onItemClickListener;

    public CustomAbsTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand,
                                        OnItemClickListener onItemClickListener) {
        super(mTree, context, datas, iconExpand, iconNoExpand);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_selection_item,
                parent, false);
        return new MyHoder(view);
    }


    class MyHoder extends RecyclerView.ViewHolder {

        public CheckBox cb;

        public TextView label;
        public LinearLayout pick_item;
        public ImageView icon;
        public LinearLayout llIcon;
        public LinearLayout llContent;
        public View mView;
        public TextView order;

        public MyHoder(View itemView) {
            super(itemView);
            this.mView = itemView;
            pick_item = itemView.findViewById(R.id.pick_item);
            cb = itemView.findViewById(R.id.cb_select_tree);
            llContent = itemView.findViewById(R.id.ll_content);
            label = itemView.findViewById(R.id.id_treenode_label);
            order = itemView.findViewById(R.id.id_treenode_order);
            Node.textSize = label.getTextSize();
            llIcon = itemView.findViewById(R.id.ll_icon);
            icon = itemView.findViewById(R.id.icon);
        }

        public View getView() {
            return mView;
        }
    }

    /**
     * 获取到所有选中的Node
     *
     * @return
     */
    public List<Node> getCheckedNodeList() {
        List<Node> list = new ArrayList<>();
        for (Node node : mAllNodes) {
            if (node.isChecked()) {
                list.add(node);
            }
        }
        return list;
    }
}
