package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.enums.OperateLogTargetType;
import com.ikamobile.pa.common.exception.BusinessException;
import com.ikamobile.pa.common.utils.ListUtils;
import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.OperateLogDao;
import com.ikamobile.pa.dao.model.OperateLog;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.OperateLogService;
import com.ikamobile.pa.thrift.common.OperateLogDto;
import com.ikamobile.pa.thrift.common.TBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by guest on 16/7/13.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogDao operateLogDao;

    @Override
    public List<OperateLogDto> listOperateLogs(OperateLogTargetType targetType, String targetId, Integer level) {
        CriteriaQuery query = new CriteriaQuery();
        List<CriteriaQuery.Sort> sorts = new ArrayList<CriteriaQuery.Sort>(){{
            add(new CriteriaQuery.Sort(OperateLog.FIELD_CREATE_TIME, CriteriaQuery.Sort.Direction.ASC));
        }};
        query.setSorts(sorts);
        Criteria criteria = query.or();
        if(StringUtils.isNotBlank(targetId)){
            criteria.andEqualTo(OperateLog.FIELD_TARGET_ID, targetId);
        }
        if(targetType != null){
            criteria.andEqualTo(OperateLog.FIELD_TARGET_TYPE, targetType);
        }
        if(level != null){
            criteria.andGreaterThanOrEqualTo(OperateLog.FIELD_LEVEL, level);
        }
        List<OperateLog> operateLogList = operateLogDao.selectByCriteriaQuery(query);
        if(ListUtils.isNullOrEmpty(operateLogList)){
            return new ArrayList<>();
        }

        List<OperateLogDto> operateLogDtoList = new ArrayList<>();
        for(OperateLog operateLog : operateLogList){
            operateLogDtoList.add(convertToOperateLogDto(operateLog));
        }

        return operateLogDtoList;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean saveOperateLog(OperateLogTargetType targetType, String targetId, Integer level, String message, String operatorName) {
        //数据验证
        if(targetType == null || StringUtils.isNullOrEmpty(targetId)||level == null){
            throw new BusinessException("targetType,targetId,level都不能为空");
        }

        OperateLog operateLog = new OperateLog();
        operateLog.setTargetType(targetType);
        operateLog.setTargetId(targetId);
        operateLog.setLevel(level);
        operateLog.setOperation(message);
        operateLog.setOperator(operatorName);
        operateLogDao.insertSelective(operateLog);
        return true;
    }


    @Override
    public boolean orderBack(String orderId, String message, String operatorName) {
        return saveOperateLog(OperateLogTargetType.ORDER,orderId,OperateLog.LEVEL_DISPATCHER_SEE,message,operatorName);
    }

    @Override
    public boolean orderMid(String orderId, String message, String operatorName) {
        return saveOperateLog(OperateLogTargetType.ORDER,orderId,OperateLog.LEVEL_DRIVER_SEE,message,operatorName);
    }

    @Override
    public boolean orderFront(String orderId, String message, String operatorName) {
        return saveOperateLog(OperateLogTargetType.ORDER,orderId,OperateLog.LEVEL_USER_SEE,message,operatorName);
    }

    private OperateLogDto convertToOperateLogDto(OperateLog operateLog) {
        if(operateLog == null){
            return null;
        }

        OperateLogDto dto = new OperateLogDto();
        dto.setId(operateLog.getId());
        dto.setTargetType(operateLog.getTargetType().name());
        dto.setTargetId(operateLog.getTargetId());
        dto.setOperator(operateLog.getOperator());
        dto.setOperation(operateLog.getOperation());
        dto.setLevel(operateLog.getLevel());
        dto.setCreateTime(operateLog.getCreateTime().getTime());

        return dto;

    }
}
