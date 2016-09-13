/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao;

import com.ikamobile.pa.dao.BaseDao;
import com.ikamobile.pa.dao.model.Task;

/**
 * 
 * 任务表 mybatis数据库访问接口基类。具体业务dao接口继承改接口，结合自动生成的mybatis配置文件，完成绝大多数功能。主要封装动态查询、动态更新、动态插入、分页查询
 * 
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
public interface TaskDao extends BaseDao<String, Task> {
    
    public Task selectByIdForUpdate(String id);
}
