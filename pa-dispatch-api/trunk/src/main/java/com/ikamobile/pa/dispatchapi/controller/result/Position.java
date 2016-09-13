package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class Position {
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 上车地点维度
     */
    private Double latitude;
    /**
     * 所属区域,如武侯区详细信息
     */
    @NotNull
    @Valid
    private CityArea area;
    /**
     * 地点描述
     */
    @NotBlank
    private String positionDesc;
}
