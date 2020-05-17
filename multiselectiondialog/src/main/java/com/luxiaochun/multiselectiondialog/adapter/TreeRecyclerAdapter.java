package com.luxiaochun.multiselectiondialog.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.viewholder.RVBaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeRecyclerAdapter extends RecyclerView.Adapter<RVBaseViewHolder> {

    protected List<Node> mAllNodes;

    public TreeRecyclerAdapter(List<Node> datas) {
        //对所有的Node进行排序
        mAllNodes = datas;
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, final int position) {
        Node node = mAllNodes.get(position);

        //设置节点点击时，可以展开以及关闭,将事件继续往外公布
        Log.i("TreeRecyclerAdapter====", node.getName() + "====" + node.isChecked());
        onBindViewHolder(node, holder, position);
    }

    @Override
    public int getItemCount() {
        return mAllNodes.size();
    }

    /**
     * 设置多选
     *
     * @param node
     * @param checked
     */
    protected void setChecked(final Node node, boolean checked) {
        node.setChecked(checked);
        notifyDataSetChanged();
    }

    /**
     * 设置单选
     *
     * @param node
     */
    protected void setRadioChecked(final Node node) {
        for (Node vNode : mAllNodes) {
            vNode.setChecked(false);
        }
        node.setChecked(true);
        notifyDataSetChanged();
    }

    /**
     * 获取到所有选中的Node
     *
     * @return
     */
    public List<Node> getCheckedNodeList() {
        List<Node> list = new ArrayList<>();
        for (Node node : mAllNodes) {
            if (node.isChecked() && node.isLeaf()) {
                list.add(node);
            }
        }
        return list;
    }

    public abstract void onBindViewHolder(Node node, RVBaseViewHolder holder, final int position);
}
