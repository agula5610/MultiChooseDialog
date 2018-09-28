package com.luxiaochun.multichoosedialog;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multichoosedialog
 * Author: jun
 * Date: 2018-08-29 14:43
 * Copyright: (C)HESC Co.,Ltd. 2016. All rights reserved.
 */
public enum CardTypeEnumMulti {
    OFFICER_CARD("1","","军官证"),

    SOLDIER_CARD("2","","士兵证"),

    SOLDIER_CARD1("21","2","士兵证1"),

    SOLDIER_CARD2("22","2","士兵证2"),

    SHENFEN_CARD("3","","身份证"),

    OTHER_CARD("9", "", "其他");


    private final String id;
    private final String pId;
    private final String name;

    CardTypeEnumMulti(String id, String pId, String name) {
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
        for (CardTypeEnumMulti type : CardTypeEnumMulti.values()) {
            mlist.add(new Node(type.getId(), type.getpId(), type.getName()));
        }
        return mlist;
    }
}
