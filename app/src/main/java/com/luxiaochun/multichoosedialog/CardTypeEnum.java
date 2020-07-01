package com.luxiaochun.multichoosedialog;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jun
 * Date: 2017-12-21 10:55
 */
public enum CardTypeEnum {
    OFFICER_CARD1("1", "军官证"),
    OFFICER_CARD2("2", "军官证"),
    OFFICER_CARD3("3", "军官证"),
    OFFICER_CARD4("4", "军官证"),
    OFFICER_CARD5("5", "军官证"),
    OFFICER_CARD6("6", "军官证"),
    OFFICER_CARD7("7", "军官证"),
    OFFICER_CARD8("8", "军官证"),
    OFFICER_CARD9("9", "军官证"),
    OFFICER_CARD10("10", "军官证"),
    OFFICER_CARD11("11", "军官证"),
    OFFICER_CARD12("12", "军官证"),
    OFFICER_CARD13("13", "军官证"),
    OFFICER_CARD14("14", "军官证"),
    OFFICER_CARD15("15", "军官证"),
    OFFICER_CARD16("16", "军官证"),
    OFFICER_CARD17("17", "军官证"),
    OFFICER_CARD18("18", "军官证"),
    OFFICER_CARD19("19", "军官证"),
    OFFICER_CARD20("20", "军官证"),
    OFFICER_CARD21("21", "军官证"),
    OFFICER_CARD22("22", "军官证"),
    OFFICER_CARD23("23", "军官证"),
    OFFICER_CARD24("24", "军官证"),
    OFFICER_CARD25("25", "军官证"),
    OFFICER_CARD26("26", "军官证"),
    OFFICER_CARD27("27", "军官证"),
    OFFICER_CARD28("28", "军官证"),
    OFFICER_CARD29("29", "军官证"),
    OFFICER_CARD30("30", "军官证"),
    OFFICER_CARD31("31", "军官证"),
    OFFICER_CARD32("32", "军官证"),
    OTHER_CARD14("28", "其他");


    private final String id;
    private final String name;

    CardTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
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
            mlist.add(new Node(type.getId(), "", type.getName()));
        }
        return mlist;
    }
}
