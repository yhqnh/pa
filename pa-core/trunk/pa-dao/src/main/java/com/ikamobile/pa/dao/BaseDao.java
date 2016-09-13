package com.ikamobile.pa.dao;
import com.ikamobile.pa.dao.param.DeleteByCriteriaQueryParam;
import com.ikamobile.pa.dao.param.UpdateByCriteriaQueryParam;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.CriteriaQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * mybatis数据库访问接口基类。具体业务dao接口继承该接口，结合自动生成的mybatis配置文件，完成绝大多数功能。 主要封装动态查询、动态更新、动态插入、分页查询。
 *
 * @author wanglong(a)ikamobile.com
 *
 */
public interface BaseDao<PK extends Serializable, M> {

    /**
     * 根据动态查询条件计数
     *
     * @param criteriaQuery
     * @return
     */
    public Long countByCriteriaQuery(CriteriaQuery criteriaQuery);

    /**
     * 根据动态查询条件计数
     *
     * @param criteriaQuery
     * @return
     */
    public List<M> selectByCriteriaQuery(CriteriaQuery criteriaQuery);

    /**
     * 根据动态查询条件
     *
     * @param criteriaQuery
     * @return
     */
    public M selectOneByCriteriaQuery(CriteriaQuery criteriaQuery);

    /**
     * 根据id查询单条记录
     *
     * @param id
     * @return
     */
    public M selectById(PK id);

    /**
     * 根据id删除单条记录
     *
     * @param id
     */
    public void deleteById(PK id);

    /**
     * 根据动态查询条件删除数据
     *
     * @param deleteByCriteriaQueryParam
     */
    public void deleteByCriteriaQuery(DeleteByCriteriaQueryParam deleteByCriteriaQueryParam);

    /**
     * 插入数据，使用目标表所有字段
     *
     * @param target
     */
    public void insert(Object target);

    /**
     * 插入数据，使用target所有非null字段
     *
     * @param target
     */
    public void insertSelective(Object target);

    /**
     * 根据动态查询条件修改，使用目标表所有字段
     *
     * @param updateByCriteriaQueryParam
     * @return
     */
    public int updateByCriteriaQuery(UpdateByCriteriaQueryParam updateByCriteriaQueryParam);

    /**
     * 根据动态查询条件修改，使用target所有非空条件字段
     *
     * @param updateByCriteriaQueryParam
     * @return
     */
    public int updateByCriteriaQuerySelective(UpdateByCriteriaQueryParam updateByCriteriaQueryParam);

    /**
     * 根据id修改，使用target所有非空条件字段
     *
     * @param updateByIdParam
     * @return
     */
    public int updateByIdSelective(UpdateByIdParam updateByIdParam);

    /**
     * 根据id修改，使用target所有非空条件字段
     *
     * @param target
     * @return
     */
    public int updateByIdSelective(Object target);

    /**
     * 根据id修改，使用目标所有字段
     *
     * @param updateByIdParam
     */
    public int updateById(UpdateByIdParam updateByIdParam);

    /**
     * 根据id修改，使用目标所有字段
     *
     * @param target
     */
    public int updateById(Object target);
}


