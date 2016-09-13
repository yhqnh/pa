package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class CityArea {
    //主键id
    @NotBlank
    private String id;
    //城市区域code
    private String code;
    //城市区域名称
    private String name;
}
