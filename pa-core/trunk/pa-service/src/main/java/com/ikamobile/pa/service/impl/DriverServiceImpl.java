package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.enums.BusinessExceptionCodeEnum;
import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.DriverService;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.DriverDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/14.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class DriverServiceImpl extends BaseServiceImpl implements DriverService{

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private PasswordHelper passwordHelper;

    public void registe(DriverDto param) {
        Driver driver = new Driver();
        driver.setLoginName(param.getLoginName());
        driver.setPhoneNumber(param.getPhoneNumber());
        String salt = passwordHelper.genSalt();
        driver.setSalt(salt);
        driver.setPassword(passwordHelper.encryptPassword(param.getPassword(), salt));
        Date date = new Date();
        driver.setCreateTime(date);
        driver.setUpdateTime(date);
        driverDao.insert(driver);
    }

    private DriverDto get(String phoneNumber) throws TBusinessException, TException {
        DriverDto driverDto = null;
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(Driver.FIELD_PHONE_NUMBER, phoneNumber);
        List<Driver> driverList = driverDao.selectByCriteriaQuery(query);
        if (!StringUtils.isEmpty(driverList) && driverList.size() > 0) {
            driverDto = new DriverDto();
            convert(driverList.get(0), driverDto);
        } else {
            throw new TBusinessException(BusinessExceptionCodeEnum.USER_NAME_EX.getCode(),BusinessExceptionCodeEnum.USER_NAME_EX.getMessage());
        }
        return driverDto;
    }

    @Override
    public DriverDto login(String phoneNumber, String pasword) throws TBusinessException, TException {
        DriverDto driverDto = get(phoneNumber);
        if(!driverDto.getPassword().equals(passwordHelper.encryptPassword(pasword,driverDto.getSalt()))){
            throw new TBusinessException(BusinessExceptionCodeEnum.USER_PASSWORD_EX.getCode(),BusinessExceptionCodeEnum.USER_PASSWORD_EX.getMessage());
        }
        return driverDto;
    }
}
