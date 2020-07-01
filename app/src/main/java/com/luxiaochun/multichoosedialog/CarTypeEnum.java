package com.luxiaochun.multichoosedialog;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multichoosedialog
 * Author: jun
 * Date: 2020-06-30 16:35
 */
public enum CarTypeEnum {
    OFFICER_CARD1("1", "军官证"),
    OFFICER_CARD2("2", "军官证"),
    OFFICER_CARD3("3", "军官证"),
    OTHER_CARD14("28", "其他");


    private final String id;
    private final String name;

    CarTypeEnum(String id, String name) {
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
        for (CarTypeEnum type : CarTypeEnum.values()) {
            mlist.add(new Node(type.getId(), "", type.getName()));
        }
        return mlist;
    }
}
