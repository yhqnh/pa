/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 机场 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Airports implements Serializable {

    public static final String CTU_AIRPORT_ID = "fea347fd-4a51-11e6-9380-7cd30ab3ecb4";
    public static final String CTU_AIRPORT_NAME = "成都双流国际机场";
    // alias
    public static final String TABLE = "airports";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of code
    public static final String FIELD_CODE = "code";
    // column name of fullName
    public static final String FIELD_FULL_NAME = "full_name";
    // column name of miniName
    public static final String FIELD_MINI_NAME = "mini_name";
    // column name of shortName
    public static final String FIELD_SHORT_NAME = "short_name";
    // column name of shortNameSpell
    public static final String FIELD_SHORT_NAME_SPELL = "short_name_spell";
    // column name of cityId
    public static final String FIELD_CITY_ID = "city_id";
    // column name of timeZone
    public static final String FIELD_TIME_ZONE = "time_zone";
    // column name of latitude
    public static final String FIELD_LATITUDE = "latitude";
    // column name of longitude
    public static final String FIELD_LONGITUDE = "longitude";
    // column name of icaoCode
    public static final String FIELD_ICAO_CODE = "icao_code";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";
    // column name of contactTel
    public static final String FIELD_CONTACT_TEL = "contact_tel";
    // column name of fax
    public static final String FIELD_FAX = "fax";
    // column name of email
    public static final String FIELD_EMAIL = "email";

    // columns START
    /**
     * uuid,自增编号
     * db_column: id 
     */    
    private String id;
    /**
     * 机场编码
     * db_column: code 
     */    
    private String code;
    /**
     * 机场全称。如:成都双流国际机场
     * db_column: full_name 
     */    
    private String fullName;
    /**
     * 机场缩写。如:双流
     * db_column: mini_name 
     */    
    private String miniName;
    /**
     * 机场简称。如:成都双流
     * db_column: short_name 
     */    
    private String shortName;
    /**
     * 机场简称拼音。如:cheng du shuang liu
     * db_column: short_name_spell 
     */    
    private String shortNameSpell;
    /**
     * 所属城市uuid
     * db_column: city_id 
     */    
    private String cityId;
    /**
     * 时区
     * db_column: time_zone 
     */    
    private String timeZone;
    /**
     * 纬度
     * db_column: latitude 
     */    
    private Float latitude;
    /**
     * 经度
     * db_column: longitude 
     */    
    private Float longitude;
    /**
     * 四字码
     * db_column: icao_code 
     */    
    private String icaoCode;
    /**
     * 创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    /**
     * 最后更新时间
     * db_column: update_time 
     */    
    private Date updateTime;
    /**
     * 联系电话
     * db_column: contact_tel 
     */    
    private String contactTel;
    /**
     * 传真
     * db_column: fax 
     */    
    private String fax;
    /**
     * 
     * db_column: email 
     */    
    private String email;
    // columns END


}
