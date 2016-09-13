package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.enums.BusinessExceptionCodeEnum;
import com.ikamobile.pa.common.enums.VerityCodeEnum;
import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.UserDao;
import com.ikamobile.pa.dao.model.User;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.UserService;
import com.ikamobile.pa.service.VerityCodeService;
import com.ikamobile.pa.thrift.common.OperateCode;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/1.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private VerityCodeService verityCodeService;

    @Override
    public UserDto get(String phoneNumber) throws TBusinessException, TException {
        UserDto userDto = null;
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(User.FIELD_PHONE_NUMBER, phoneNumber);
        List<User> userList = userDao.selectByCriteriaQuery(query);
        if (!StringUtils.isEmpty(userList) && userList.size() > 0) {
            userDto = new UserDto();
            BeanUtils.copyProperties(userList.get(0), userDto);
        }
        return userDto;
    }

    public void register(UserParam param) {
        User user = new User();
        user.setPhoneNumber(param.getPhoneNumber());
        String salt = passwordHelper.genSalt();
        user.setSalt(salt);
        user.setPassword(passwordHelper.encryptPassword(param.getPassword(), salt));
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userDao.insert(user);
    }

    @Override
    public UserDto login(String phoneNumber, String verityCode) throws TBusinessException, TException {
        UserDto userDto = get(phoneNumber);
        if (StringUtils.isEmpty(userDto)) {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            userDao.insertSelective(user);

            userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
        }
        OperateResponse operateResponse = verityCodeService.isValid(phoneNumber, VerityCodeEnum.REGISTER.name(), verityCode);
        if (operateResponse.getOperateCode().equals(OperateCode.fail)) {
            throw new TBusinessException(BusinessExceptionCodeEnum.VERITYCODE_INVALID.getCode(), operateResponse.getMessage());
        }
        return userDto;
    }
}
