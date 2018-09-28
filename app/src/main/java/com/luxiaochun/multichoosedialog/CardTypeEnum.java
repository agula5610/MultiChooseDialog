package com.luxiaochun.multichoosedialog;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jun
 * Date: 2017-12-21 10:55
 */
public enum CardTypeEnum {
    OFFICER_CARD("1", "", "军官证"),
    SOLDIER_CARD("2", "", "士兵证"),
    SOLDIER_CARD1("21", "", "士兵证1"),
    SOLDIER_CARD2("22", "", "士兵证2"),
    SHENFEN_CARD("3", "", "身份证"),
    OTHER_CARD("9", "", "其他"),
    OTHER_CARD1("9", "", "其他"),
    OTHER_CARD2("10", "", "其他"),
    OTHER_CARD3("11", "", "其他"),
    OTHER_CARD4("12", "", "其他"),
    OTHER_CARD5("13", "", "其他"),
    OTHER_CARD6("14", "", "其他"),
    OTHER_CARD7("15", "", "其他"),
    OTHER_CARD8("16", "", "其他"),
    OTHER_CARD9("17", "", "其他"),
    OTHER_CARD10("18", "", "其他"),
    OTHER_CARD11("19", "", "其他"),
    OTHER_CARD12("20", "", "其他"),
    OTHER_CARD13("21", "", "其他"),
    OTHER_CARD14("22", "", "其他");


    private final String id;
    private final String pId;
    private final String name;

    CardTypeEnum(String id, String pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
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

    /**
     * 返回类型列表
     *
     * @return
     */
    public static List<Node> getDatas() {
        List<Node> mlist = new ArrayList<>();
        for (CardTypeEnum type : CardTypeEnum.values()) {
            mlist.add(new Node(type.getId(), type.getpId(), type.getName()));
        }
        return mlist;
    }
}
