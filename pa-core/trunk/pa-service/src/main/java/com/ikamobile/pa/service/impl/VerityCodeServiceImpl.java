package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.utils.ListUtils;
import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.VerifyCodeDao;
import com.ikamobile.pa.dao.model.VerifyCode;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.SmsService;
import com.ikamobile.pa.service.VerityCodeService;
import com.ikamobile.pa.thrift.common.OperateCode;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.TBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/1.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class VerityCodeServiceImpl implements VerityCodeService {

    @Autowired
    private VerifyCodeDao verifyCodeDao;

    @Autowired
    private SmsService smsService;

    /**
     * 获取验证码
     *
     * @param phoneNumber 电话号码
     * @param type        类型，枚举VerityCodeEnum
     * @return
     * @throws TBusinessException
     * @throws TException
     */
    @Override
    public OperateResponse get(String phoneNumber, String type) throws TBusinessException, TException {
        OperateResponse operateResponse = new OperateResponse();
        String temp = StringUtils.createRandom(4);
        VerifyCode verifyCode = null;
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(VerifyCode.FIELD_PHONE_NUMBER, phoneNumber);
        criteria.andEqualTo(VerifyCode.FIELD_TYPE, type);
        List<VerifyCode> verifyCodeList = verifyCodeDao.selectByCriteriaQuery(query);
        if (!ListUtils.isNullOrEmpty(verifyCodeList) && verifyCodeList.size() > 0) {
            verifyCode = verifyCodeList.get(0);

            DateTime updateTime = new DateTime(verifyCode.getUpdateTime());
            DateTime now = new DateTime();
            DateTime tempTime = now.minusMinutes(1);
            if(tempTime.isBefore(updateTime)){
                operateResponse.setMessage("一分钟之内只能获取一次");
                operateResponse.setOperateCode(OperateCode.fail);
                return operateResponse;
            }

            verifyCode.setVerifyCode(temp);
            verifyCode.setUpdateTime(new Date());
            UpdateByIdParam param = new UpdateByIdParam(verifyCode.getId(), verifyCode);
            verifyCodeDao.updateById(param);
        } else {
            verifyCode = new VerifyCode();
            verifyCode.setPhoneNumber(phoneNumber + "");
            verifyCode.setType(type);
            verifyCode.setVerifyCode(temp);
            verifyCodeDao.insertSelective(verifyCode);
        }
        verifyCode.setVerifyCode(temp);
        smsService.sendVerifyCode(phoneNumber + "", temp, null);
        return new OperateResponse();
    }

    /**
     * 验证码是否合法
     *
     * @param phoneNumber 电话号码
     * @param type        类型，枚举VerityCodeEnum
     * @param verifyCode  验证码
     * @return
     * @throws TBusinessException
     * @throws TException
     */
    @Override
    public OperateResponse isValid(String phoneNumber, String type, String verifyCode) throws TBusinessException, TException {
        log.info("the veriftyCode info: phoneNuber:{},type:{},veriftyCode:{}",phoneNumber,type,verifyCode);
        OperateResponse operateResponse = new OperateResponse();
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(VerifyCode.FIELD_PHONE_NUMBER, phoneNumber);
        criteria.andEqualTo(VerifyCode.FIELD_TYPE, type);
        criteria.andEqualTo(VerifyCode.FIELD_VERIFY_CODE, verifyCode);
        List<VerifyCode> verifyCodeList = verifyCodeDao.selectByCriteriaQuery(query);

        VerifyCode verifyCodeEntity = null;
        if (ListUtils.isNullOrEmpty(verifyCodeList) || verifyCodeList.size() < 0) {
            operateResponse.setOperateCode(OperateCode.fail);
            operateResponse.setMessage("验证码不合法");
            return operateResponse;
        } else {
            verifyCodeEntity = verifyCodeList.get(0);
        }

        DateTime updateTime = new DateTime(verifyCodeEntity.getUpdateTime());
        DateTime validUpdateTime = updateTime.plusMinutes(5);
        if (validUpdateTime.isBeforeNow()) {
            operateResponse.setOperateCode(OperateCode.fail);
            operateResponse.setMessage("验证码已过期");
            return operateResponse;
        }
        return operateResponse;
    }
}
