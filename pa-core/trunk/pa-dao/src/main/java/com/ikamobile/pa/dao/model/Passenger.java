/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import com.ikamobile.pa.common.enums.CertificateTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 乘客表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Passenger implements Serializable {
    
    // alias
    public static final String TABLE = "passenger";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of name
    public static final String FIELD_NAME = "name";
    // column name of sex
    public static final String FIELD_SEX = "sex";
    // column name of certificateType
    public static final String FIELD_CERTIFICATE_TYPE = "certificate_type";
    // column name of certificateNumber
    public static final String FIELD_CERTIFICATE_NUMBER = "certificate_number";
    // column name of birthday
    public static final String FIELD_BIRTHDAY = "birthday";
    // column name of orderId
    public static final String FIELD_ORDER_ID = "order_id";
    // column name of age
    public static final String FIELD_AGE = "age";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 
     * db_column: id 
     */    
    private String id;
    /**
     * 姓名
     * db_column: name 
     */    
    private String name;
    /**
     * 性别（0-未知，1-男，2-女）
     * db_column: sex 
     */    
    private Integer sex;
    /**
     * 证件类型（OTHERS=其他,IDENTITY_CARD=身份证,PASSPORT=护照,HKM_PASS=港澳通行证,TW_PASS=台湾通行证,MTPs=台胞证,HOME_RETURN_PERMIT=返乡证
     * db_column: certificate_type 
     */    
    private CertificateTypeEnum certificateType;
    /**
     * 证件号码
     * db_column: certificate_number 
     */    
    private String certificateNumber;
    /**
     * 生日
     * db_column: birthday 
     */    
    private Date birthday;
    /**
     * 
     * db_column: order_id 
     */    
    private String orderId;
    /**
     * 年龄
     * db_column: age 
     */    
    private Integer age;
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
