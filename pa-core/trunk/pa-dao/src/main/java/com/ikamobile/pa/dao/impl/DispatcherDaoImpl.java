/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.impl;

import java.util.List;
import com.ikamobile.pa.dao.BaseDao;
import com.ikamobile.pa.dao.DispatcherDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.param.DeleteByCriteriaQueryParam;
import com.ikamobile.pa.dao.param.UpdateByCriteriaQueryParam;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.dao.model.Dispatcher;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 调度者表 mybatis数据库访问接口基类。具体业务dao接口继承改接口，结合自动生成的mybatis配置文件，完成绝大多数功能。主要封装动态查询、动态更新、动态插入、分页查询
 *
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Repository
public class DispatcherDaoImpl extends BaseDaoImpl implements DispatcherDao {

    /**
     * 根据动态查询条件计数
     *
     * @param criteriaQuery
     * @return
     */
    public Long countByCriteriaQuery(CriteriaQuery criteriaQuery) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).countByCriteriaQuery(criteriaQuery);
    }

    /**
     * 根据动态查询条件计数
     *
     * @param criteriaQuery
     * @return
     */
    public List<Dispatcher> selectByCriteriaQuery(CriteriaQuery criteriaQuery) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).selectByCriteriaQuery(criteriaQuery);
    }

    /**
     * 根据id查询单条记录
     *
     * @param id
     * @return
     */
    public Dispatcher selectById(String id) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).selectById(id);
    }

    /**
     * 根据id删除单条记录
     *
     * @param id
     */
    public void deleteById(String id) {
        sqlSessionTemplate.getMapper(DispatcherDao.class).deleteById(id);
    }

    /**
     * 根据动态查询条件删除数据
     *
     * @param deleteByCriteriaQueryParam
     */
    public void deleteByCriteriaQuery(DeleteByCriteriaQueryParam deleteByCriteriaQueryParam) {
        sqlSessionTemplate.getMapper(DispatcherDao.class).deleteByCriteriaQuery(deleteByCriteriaQueryParam);
    }

    /**
     * 插入数据，使用目标表所有字段
     *
     * @param target
     */
    public void insert(Object target) {
        sqlSessionTemplate.getMapper(DispatcherDao.class).insert(target);
    }

    /**
     * 插入数据，使用target所有非null字段
     *
     * @param target
     */
    public void insertSelective(Object target) {
        sqlSessionTemplate.getMapper(DispatcherDao.class).insertSelective(target);
    }

    /**
     * 根据动态查询条件修改，使用目标表所有字段
     *
     * @param updateByCriteriaQueryParam
     * @return
     */
    public int updateByCriteriaQuery(UpdateByCriteriaQueryParam updateByCriteriaQueryParam) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateByCriteriaQuery(updateByCriteriaQueryParam);
    }

    /**
     * 根据动态查询条件修改，使用target所有非空条件字段
     *
     * @param updateByCriteriaQueryParam
     * @return
     */
    public int updateByCriteriaQuerySelective(UpdateByCriteriaQueryParam updateByCriteriaQueryParam) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateByCriteriaQuerySelective(updateByCriteriaQueryParam);
    }

    /**
     * 根据id修改，使用target所有非空条件字段
     *
     * @param updateByIdParam
     * @return
     */
    public int updateByIdSelective(UpdateByIdParam updateByIdParam) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateByIdSelective(updateByIdParam);
    }

    /**
     * 根据id修改，使用目标所有字段
     *
     * @param updateByIdParam
     */
    public int updateById(UpdateByIdParam updateByIdParam) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateById(updateByIdParam);
    }

    /**
     * 根据id修改，使用target所有非空条件字段
     *
     * @param target
     * @return
     */
    public int updateByIdSelective(Object target) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateByIdSelective(target);
    }

    /**
     * 根据id修改，使用目标所有字段
     *
     * @param target
     */
    public int updateById(Object target) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).updateById(target);
    }

    /**
     * 根据动态查询条件
     *
     * @param criteriaQuery
     * @return
     */
    public Dispatcher selectOneByCriteriaQuery(CriteriaQuery criteriaQuery) {
        return sqlSessionTemplate.getMapper(DispatcherDao.class).selectOneByCriteriaQuery(criteriaQuery);
    }
}
