/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.service;

import javax.annotation.Resource;
import javax.sql.DataSource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ikamobile.tmcs.service.DdAuthCorpService;

/**
 * 
 * 钉钉授权企业信息 Service Test
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {  "ddAuthCorpService", "dataSource" }) //这里需要添加进所有的属性
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DdAuthCorpServiceTest extends AbstractTestNGSpringContextTests {

    @Resource
    private DataSource dataSource;
    
    @Resource
    private DdAuthCorpService ddAuthCorpService;

    @BeforeClass
    protected void prepareDatabase() {
        if (this.applicationContext.getResource("classpath:com/ikamobile/pa/service/DdAuthCorpServiceTestData.sql").exists()) {
            DatabasePopulator databasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", this.applicationContext.getResource("classpath:com/ikamobile/pa/service/DdAuthCorpServiceTestData.sql"));
            DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        }
    }
    
}
