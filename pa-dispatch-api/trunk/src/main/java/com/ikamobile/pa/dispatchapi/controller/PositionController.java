package com.ikamobile.pa.dispatchapi.controller;


import com.ikamobile.pa.dispatchapi.controller.result.AreaDetail;
import com.ikamobile.pa.dispatchapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.PositionServiceClientProxy;
import com.ikamobile.pa.thrift.server.acceptor.AreaDto;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Resource
    private PositionServiceClientProxy positionServiceClientProxy;

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
