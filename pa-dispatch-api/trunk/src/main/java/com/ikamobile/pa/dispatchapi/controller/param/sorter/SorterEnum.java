package com.ikamobile.pa.dispatchapi.controller.param.sorter;

/**
 * Created by guest on 16/7/18.
 */
public enum  SorterEnum {
    ASC(1),
    DESC(2);
    private Integer value;

    SorterEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

