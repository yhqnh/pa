package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.dao.AreaDao;
import com.ikamobile.pa.dao.CityDao;
import com.ikamobile.pa.dao.model.Area;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.PositionService;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.AreaDto;
import org.apache.thrift.TException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcheng on 2016/7/13.
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class PositionServiceImpl implements PositionService {

    @Resource
    private AreaDao areaDao;
    @Resource
    private CityDao cityDao;


    @Override

    public List<AreaDto> getAreas(String areaCode) throws TException {
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(Area.FIELD_CODE,areaCode);
        List<Area> areas = areaDao.selectByCriteriaQuery(query);
        if(areas==null||areas.isEmpty()){
            return null;
        }else{
            return getAreasByCityId(areas.get(0).getBelongCityId());
        }

    }

    @Override
    public List<AreaDto> getAreasByCityId(String cityId) throws TException {
        List<Area> areas = getCityAreas(cityId);
        return transform(areas);
    }

    @Override
    public List<AreaDto> getAllArea() throws TException{
        List<Area> areas = areaDao.selectByCriteriaQuery(null);
        return transform(areas);
    }

    private List<AreaDto> transform(List<Area> areas) {
        List<AreaDto> list = new ArrayList<>();
        for (Area area : areas) {
            AreaDto dto = new AreaDto();
            dto.setName(area.getName());
            dto.setId(area.getId());
            dto.setCode(area.getCode());
            list.add(dto);
        }
        return list;
    }

    @Cacheable(value = "cityArea",key = "#cityId")
    public List<Area> getCityAreas(String cityId) throws TException {
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(Area.FIELD_BELONG_CITY_ID,cityId);
        List<Area> areas = areaDao.selectByCriteriaQuery(query);
        return areas;
    }
}
