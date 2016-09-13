<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

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

import com.ikamobile.tmcs.service.${className}Service;

/**
 * 
 * ${table.remarks} Service Test
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {  "${classNameLower}Service", "dataSource" }) //这里需要添加进所有的属性
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ${className}ServiceTest extends AbstractTestNGSpringContextTests {

    @Resource
    private DataSource dataSource;
    
    @Resource
    private ${className}Service ${classNameLower}Service;

    @BeforeClass
    protected void prepareDatabase() {
        if (this.applicationContext.getResource("classpath:${basepackage_dir}/service/${className}ServiceTestData.sql").exists()) {
            DatabasePopulator databasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", this.applicationContext.getResource("classpath:${basepackage_dir}/service/${className}ServiceTestData.sql"));
            DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        }
    }
    
}
