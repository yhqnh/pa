/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;

/**
 * 
 * 钉钉授权企业信息 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class DdAuthCorp implements Serializable {
    
    // alias
    public static final String TABLE = "dd_auth_corp";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of corpId
    public static final String FIELD_CORP_ID = "corp_id";
    // column name of corpName
    public static final String FIELD_CORP_NAME = "corp_name";
    // column name of industry
    public static final String FIELD_INDUSTRY = "industry";
    // column name of authenticated
    public static final String FIELD_AUTHENTICATED = "authenticated";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 
     * db_column: id 
     */    
    private Integer id;
    /**
     * 钉钉企业ID
     * db_column: corp_id 
     */    
    private String corpId;
    /**
     * 钉钉企业名称
     * db_column: corp_name 
     */    
    private String corpName;
    /**
     * 所属行业
     * db_column: industry 
     */    
    private String industry;
    /**
     * 是否是认证企业
     * db_column: authenticated 
     */    
    private Boolean authenticated;
    /**
     * 创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    /**
     * 更新时间
     * db_column: update_time 
     */    
    private Date updateTime;
    // columns END


}
