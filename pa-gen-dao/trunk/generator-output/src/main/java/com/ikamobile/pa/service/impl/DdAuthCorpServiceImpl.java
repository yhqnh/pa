/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikamobile.common.service.impl.BaseServiceImpl;
import com.ikamobile.tmcs.service.DdAuthCorpService;
import com.ikamobile.tmcs.dao.DdAuthCorpDao;

import lombok.Getter;

/**
 * 
 * 钉钉授权企业信息 Service
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Getter
@Service
@Transactional
public class DdAuthCorpServiceImpl extends BaseServiceImpl implements DdAuthCorpService {

    @Resource
    private DdAuthCorpDao ddAuthCorpDao;
    
}
