package com.luxiaochun.multiselectiondialog.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<Node> mVisibleNodes = new ArrayList<>();
    protected LayoutInflater mInflater;

    /**
     * 存储所有的Node
     */
    protected List<Node> mAllNodes = new ArrayList<>();

    /**
     * 展开与关闭的图片
     */
    private int iconExpand = -1, iconNoExpand = -1;

    public TreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int iconExpand, int iconNoExpand) {

        this.iconExpand = iconExpand;
        this.iconNoExpand = iconNoExpand;

        for (Node node : datas) {
            node.getChildren().clear();
            node.iconExpand = iconExpand;
            node.iconNoExpand = iconNoExpand;
        }
        mContext = context;
        /**
         * 对所有的Node进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(datas);
        /**
         *  对所有节点分级
         */
        TreeHelper.grading(mAllNodes);
        /**
         * 过滤出可见的Node
         */
        mVisibleNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Node node = mVisibleNodes.get(position);
        // 设置内边距
        holder.itemView.setPadding(node.getLevel() * 30, 3, 3, 3);
        /**
         * 设置节点点击时，可以展开以及关闭,将事件继续往外公布
         */
        onBindViewHolder(node, holder, position);
    }

    @Override
    public int getItemCount() {
        return mVisibleNodes.size();
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        Node n = mVisibleNodes.get(position);
        if (n != null) { // 排除传入参数错误异常
            if (!n.isLeaf()) {
                boolean isExpand = n.isExpand();
                n.setExpand(!isExpand);
                //展开跟收缩不一样，展开是一级一级展开，而收缩是父类以下全部收缩
                //展开情况
                if (n.isExpand()) {
                    List<Node> list = n.getChildren();
                    for (Node node : list) {
                        node.setExpand(false);
                        node.setVisible(true);
                    }
                }
                //收缩情况
                else {
                    Collapse(n);
                }
                mVisibleNodes = TreeHelper.filterVisibleNode(mAllNodes);
            }
            notifyDataSetChanged();// 刷新视图
        }
    }

    //收缩情况
    private void Collapse(Node n) {
        List<Node> list = n.getChildren();
        if (list != null && list.size() > 0) {
            for (Node node : list) {
                node.setExpand(false);
                node.setVisible(false);
                Collapse(node);
            }
        }
    }

    /**
     * 设置多选
     *
     * @param node
     * @param checked
     */
    protected void setChecked(final Node node, boolean checked) {
        node.setChecked(checked);
        setChildChecked(node, checked);
        if (node.getParent() != null)
            setNodeParentChecked(node.getParent(), checked);
        notifyDataSetChanged();
    }

    /**
     * 设置是否选中
     *
     * @param node
     * @param checked
     */
    public <T, B> void setChildChecked(Node<T, B> node, boolean checked) {
        if (!node.isLeaf()) {
            node.setChecked(checked);
            for (Node childrenNode : node.getChildren()) {
                setChildChecked(childrenNode, checked);
            }
        } else {
            node.setChecked(checked);
        }
    }

    private void setNodeParentChecked(Node node, boolean checked) {
        if (checked) {
            List<Node> list = node.getChildren();
            boolean isParentChecked = false;
            for (Node n : list) {
                isParentChecked = checked && n.isChecked();
            }
            if (isParentChecked) {
                node.setChecked(checked);
            }
            if (node.getParent() != null)
                setNodeParentChecked(node.getParent(), checked);
        } else {
            List<Node> childrens = node.getChildren();
            boolean isChecked = false;
            for (Node children : childrens) {
                if (children.isChecked()) {
                    isChecked = true;
                }
            }
            //如果所有自节点都没有被选中 父节点也不选中
            if (!isChecked) {
                node.setChecked(checked);
            }
            if (node.getParent() != null)
                setNodeParentChecked(node.getParent(), checked);
        }
    }

    public abstract void onBindViewHolder(Node node, RecyclerView.ViewHolder holder, final int position);
}
