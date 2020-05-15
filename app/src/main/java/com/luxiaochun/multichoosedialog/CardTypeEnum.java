package com.luxiaochun.multichoosedialog;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jun
 * Date: 2017-12-21 10:55
 */
public enum CardTypeEnum {
    OFFICER_CARD("1", "", "军官证", false),
    SOLDIER_CARD("2", "", "士兵证", false),
    SOLDIER_CARD1("21", "", "士兵证1", false),
    SOLDIER_CARD2("22", "", "士兵证2", false),
    SHENFEN_CARD("3", "", "身份证", false),
    OTHER_CARD("9", "", "其他", false),
    OTHER_CARD14("22", "", "其他", false);


    private final String id;
    private final String pId;
    private final String name;
    private boolean isSelected;

    CardTypeEnum(String id, String pId, String name, boolean isSelected) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public String getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * 返回类型列表
     *
     * @return
     */
    public static List<Node> getDatas() {
        List<Node> mlist = new ArrayList<>();
        for (CardTypeEnum type : CardTypeEnum.values()) {
            mlist.add(new Node(type.getId(), type.getpId(), type.getName(), type.isSelected()));
        }
        return mlist;
    }

    /**
     * 返回类型列表
     *
     * @return
     */
    public static String setDatas(List<Node> nodes) {
        StringBuilder sb = new StringBuilder();
        for (CardTypeEnum type : CardTypeEnum.values()) {
            type.setSelected(false);
        }
        for (Node node : nodes) {
            sb.append(node.getName() + ",");
            if (node.isChecked()){
                for (CardTypeEnum type : CardTypeEnum.values()) {
                    if (node.getId() == type.getId()){
                        type.setSelected(true);
                    }
                }
            }
        }
        return sb.toString();
    }
}
