package com.ikamobile.pa.dispatchapi.controller.param;

import com.ikamobile.pa.dispatchapi.controller.param.sorter.OrderQuerySorterParam;
import lombok.Data;

/**
 * Created by guest on 16/7/19.
 */
@Data
public class OrderSorterPageParam extends PageParam {
    /**
     * 排序信息
     */
    private OrderQuerySorterParam sorter;
}
