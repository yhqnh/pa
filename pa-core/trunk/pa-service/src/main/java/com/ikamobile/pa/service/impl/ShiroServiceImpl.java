package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.enums.BusinessExceptionCodeEnum;
import com.ikamobile.pa.dao.DispatcherDao;
import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.UserDao;
import com.ikamobile.pa.dao.model.Dispatcher;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.User;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.ShiroService;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.PaUserDto;
import com.ikamobile.pa.thrift.server.acceptor.PaUserType;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/1.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DispatcherDao dispatcherDao;

    @Autowired
    private DriverDao driverDao;

    @Override
    @Transactional(readOnly = true)
    public PaUserDto getPaUser(String username) throws TBusinessException, TException {
        Handler userHandler = new UserHandler();
        Handler dispatchHandler = new DispatchHandler();
        Handler driverHandler = new DriverHandler();
        userHandler.setNextHandler(driverHandler);
        driverHandler.setNextHandler(dispatchHandler);
        PaUserDto paUserDto = userHandler.handle(username);
        log.info("paUserDto is {}", paUserDto);
        return paUserDto;
    }

    abstract class Handler {
        protected Handler nextHandler;

        public abstract PaUserDto handle(String userame) throws TBusinessException;

        public Handler getNextHandler() {
            return nextHandler;
        }

        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }
    }

    class UserHandler extends Handler {

        @Override
        public PaUserDto handle(String username) throws TBusinessException {
            CriteriaQuery query = new CriteriaQuery();
            Criteria criteria = query.createCriteria();
            criteria.andEqualTo(User.FIELD_PHONE_NUMBER, username);
            List<User> userList = userDao.selectByCriteriaQuery(query);
            if (!StringUtils.isEmpty(userList) && userList.size() > 0) {
                if (userList.size() == 1) {
                    User user = userList.get(0);
                    PaUserDto paUserDto = new PaUserDto();
                    paUserDto.setId(user.getId());
                    paUserDto.setPasswd(user.getPhoneNumber());
                    paUserDto.setSalt(user.getSalt());
                    paUserDto.setUsername(user.getPhoneNumber());
                    paUserDto.setPaUserType(PaUserType.user);
                    return paUserDto;
                } else {
                    throw new TBusinessException(BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getCode(), BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getMessage());
                }
            } else {
                if (!StringUtils.isEmpty(getNextHandler())) {
                    return getNextHandler().handle(username);
                }
            }
            return null;
        }
    }

    class DispatchHandler extends Handler {

        @Override
        public PaUserDto handle(String username) throws TBusinessException {
            CriteriaQuery query = new CriteriaQuery();
            Criteria criteria = query.createCriteria();
            criteria.andEqualTo(Dispatcher.FIELD_LOGIN_NAME, username);
            List<Dispatcher> dispatcherList = dispatcherDao.selectByCriteriaQuery(query);
            if (!StringUtils.isEmpty(dispatcherList) && dispatcherList.size() > 0) {
                if (dispatcherList.size() == 1) {
                    Dispatcher dispatcher = dispatcherList.get(0);
                    PaUserDto paUserDto = new PaUserDto();
                    paUserDto.setId(dispatcher.getId());
                    paUserDto.setPasswd(dispatcher.getPassword());
                    paUserDto.setSalt(dispatcher.getSalt());
                    paUserDto.setUsername(dispatcher.getLoginName());
                    paUserDto.setPaUserType(PaUserType.dispatcher);
                    return paUserDto;
                } else {
                    throw new TBusinessException(BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getCode(), BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getMessage());
                }
            } else {
                if (!StringUtils.isEmpty(getNextHandler())) {
                    return getNextHandler().handle(username);
                }
            }
            return null;
        }
    }

    class DriverHandler extends Handler {

        @Override
        public PaUserDto handle(String username) throws TBusinessException {
            CriteriaQuery query = new CriteriaQuery();
            Criteria criteria = query.createCriteria();
            criteria.andEqualTo(User.FIELD_PHONE_NUMBER, username);
            List<Driver> driverList = driverDao.selectByCriteriaQuery(query);
            if (!StringUtils.isEmpty(driverList) && driverList.size() > 0) {
                if (driverList.size() == 1) {
                    Driver dirver = driverList.get(0);
                    PaUserDto paUserDto = new PaUserDto();
                    paUserDto.setId(dirver.getId());
                    paUserDto.setPasswd(dirver.getPassword());
                    paUserDto.setSalt(dirver.getSalt());
                    paUserDto.setUsername(dirver.getPhoneNumber());
                    paUserDto.setPaUserType(PaUserType.driver);
                    return paUserDto;
                } else {
                    throw new TBusinessException(BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getCode(), BusinessExceptionCodeEnum.ACCOUNT_MULTI_USERNAME_EXCEPTION.getMessage());
                }
            } else {
                if (!StringUtils.isEmpty(getNextHandler())) {
                    return getNextHandler().handle(username);
                }
            }
            return null;
        }
    }
}
