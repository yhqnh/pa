package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.controller.result.AreaDetail;
import com.ikamobile.pa.clientsapi.controller.result.CityArea;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.PositionServiceClientProxy;
import com.ikamobile.pa.thrift.server.acceptor.AreaDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionServiceClientProxy positionServiceClientProxy;
    /**
     * 获取城市所有的区域
     * @param areaCode 区域code
     * @return 区域列表
     */
    @RequestMapping("/areas/{areaCode}")
    public SimpleResponse<List<AreaDetail>> getArea(@PathVariable  String areaCode) throws TException {
        if(StringUtils.isNotEmpty(areaCode)){}
        List<AreaDto> areas = positionServiceClientProxy.createProxy().getAreas(areaCode);
        List<AreaDetail> result = transform(areas);
        SimpleResponse<List<AreaDetail>> listSimpleResponse = new SimpleResponse<>();
        listSimpleResponse.setData(result);
        return listSimpleResponse;
    }

    /**
     * 获取所有区域（成都市）
     * @return
     * @throws TException
     */
    @RequestMapping("/areas")
    public SimpleResponse<List<AreaDetail>> getAreas() throws TException {
        List<AreaDto> areas = positionServiceClientProxy.createProxy().getAllArea();
        List<AreaDetail> result = transform(areas);
        SimpleResponse<List<AreaDetail>> listSimpleResponse = new SimpleResponse<>();
        listSimpleResponse.setData(result);
        return listSimpleResponse;
    }

    private List<AreaDetail> transform(List<AreaDto> areas) {
        List<AreaDetail> result = new ArrayList<>();
        if(areas!=null&&areas.size()>0) {
            for (AreaDto a : areas) {
                AreaDetail d = new AreaDetail();
                d.setCode(a.getCode());
                d.setId(a.getId());
                d.setName(a.getName());
                result.add(d);
            }
        }
        return result;
    }
}
