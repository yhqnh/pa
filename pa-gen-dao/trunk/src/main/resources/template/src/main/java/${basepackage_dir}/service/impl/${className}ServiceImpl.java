<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikamobile.common.service.impl.BaseServiceImpl;
import com.ikamobile.tmcs.service.${className}Service;
import com.ikamobile.tmcs.dao.${className}Dao;

import lombok.Getter;

/**
 * 
 * ${table.remarks} Service
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Getter
@Service
@Transactional
public class ${className}ServiceImpl extends BaseServiceImpl implements ${className}Service {

    @Resource
    private ${className}Dao ${classNameLower}Dao;
    
}
