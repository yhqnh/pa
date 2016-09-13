/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ikamobile.common.dao.query.Criteria;
import com.ikamobile.common.dao.query.CriteriaQuery;
import com.ikamobile.common.dao.query.CriteriaQuery.Sort;
import com.ikamobile.tmcs.dao.DdAuthCorpDao;
import com.ikamobile.tmcs.dao.model.entity.DdAuthCorp;

/**
 * 
 * 钉钉授权企业信息 DAO Test
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {  "ddAuthCorpDao", "dataSource" }) //这里需要添加进所有的属性
@ContextConfiguration(locations = { "/daoTestApplicationContext.xml" })
public class DdAuthCorpDaoTest extends AbstractTestNGSpringContextTests {

	@Resource
	private DataSource dataSource;
	
	@Resource
	private DdAuthCorpDao ddAuthCorpDao;

	@BeforeMethod
	protected void prepareDatabase() {
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", this.applicationContext.getResource("classpath:com/ikamobile/pa/dao/DdAuthCorpDaoTestData.sql"));
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
	}
	
	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#countByCriteriaQuery(com.ikamobile.common.dao.query.CriteriaQuery)}.
	 */
	@Test(enabled = false)
	public void testCountByCriteriaQuery() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#selectByCriteriaQuery(com.ikamobile.common.dao.query.CriteriaQuery)}.
	 */
	@Test(enabled = false)
	public void testSelectByCriteriaQuery() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#selectById(java.lang.String)}.
	 */
	@Test(enabled = false)
	public void testSelectById() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#deleteById(java.lang.String)}.
	 */
	@Test(enabled = false)
	public void testDeleteById() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#deleteByCriteriaQuery(com.ikamobile.common.dao.query.CriteriaQuery)}.
	 */
	@Test(enabled = false)
	public void testDeleteByCriteriaQuery() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#inset(java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testInset() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#insertSelective(java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testInsertSelective() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#updateByCriteriaQuery(com.ikamobile.common.dao.query.CriteriaQuery, java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testUpdateByCriteriaQuery() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#updateByCriteriaQuerySelective(com.ikamobile.common.dao.query.CriteriaQuery, java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testUpdateByCriteriaQuerySelective() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#updateByIdSelective(java.lang.String, java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testUpdateByIdSelective() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ikamobile.common.dao.BaseDao#updateById(java.lang.String, java.lang.Object)}.
	 */
	@Test(enabled = false)
	public void testUpdateById() {
		Assert.fail("Not yet implemented");
	}

}
