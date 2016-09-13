package com.ikamobile.pa.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/4.
 */
@Repository
public class BaseDaoImpl {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
}
