package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.utils.ListUtils;
import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.DispatcherDao;
import com.ikamobile.pa.dao.model.Dispatcher;
import com.ikamobile.pa.dao.model.VerifyCode;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.DispatcherService;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.DispatcherDto;
import com.ikamobile.pa.thrift.server.acceptor.DispatcherParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/14.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class DispatcherServiceImpl extends BaseServiceImpl implements DispatcherService {

    @Autowired
    private DispatcherDao dispatcherDao;

    @Autowired
    private PasswordHelper passwordHelper;

    public void registe(DispatcherParam param) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setLoginName(param.getLoginName());
        dispatcher.setPhoneNumber(param.getPhoneNumber());
        String salt = passwordHelper.genSalt();
        dispatcher.setSalt(salt);
        dispatcher.setPassword(passwordHelper.encryptPassword(param.getPassword(), salt));
        Date date = new Date();
        dispatcher.setCreateTime(date);
        dispatcher.setUpdateTime(date);
        dispatcherDao.insert(dispatcher);
    }

    @Override
    public List<DispatcherDto> getAll() throws TBusinessException, TException {
        List<DispatcherDto> dispatcherDtoList = new ArrayList<>();
        List<Dispatcher> dispatcherList = dispatcherDao.selectByCriteriaQuery(null);
        if (!StringUtils.isEmpty(dispatcherList) && dispatcherList.size() > 0) {
            for (Dispatcher dispatcher : dispatcherList) {
                DispatcherDto dispatcherDto = new DispatcherDto();
                convert(dispatcher, dispatcherDto);
                dispatcherDtoList.add(dispatcherDto);
            }
        }
        return dispatcherDtoList;
    }
}
