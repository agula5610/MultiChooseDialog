package com.luxiaochun.multiselectiondialog;

import java.io.Serializable;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2018-08-21 11:19
 * Copyright: (C)HESC Co.,Ltd. 2016. All rights reserved.
 */
public enum DialogType implements Serializable {
    SINGLEDEGREE_SINGLECHOOSE,        //单级单选（最常见）
    SINGLEDEGREE_MULTICHOOSE,         //单级多选
    MULTIDEGREE_SINGLECHOOSE_LEAF,    //多级单选（最后一级可点击）
    MULTIDEGREE_SINGLECHOOSE_ALL,     //多级单选(任何一级都可点击选择)
    MULTIDEGREE_MULTICHOOSE,          //多选多级
    SINGLEDEGREE_ORDER,               //限制排序多选单级
}
