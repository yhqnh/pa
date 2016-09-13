package com.ikamobile.dao.impl;

import com.ikamobile.dao.SMSRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/11.
 */
@Service
@Slf4j
@Repository
public class SMSRecordDaoServiceImpl implements SMSRecordService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(String type,String msg,String to) {
        try {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("sms_log");
            insert.usingColumns("type", "content", "mobile", "create_time", "id");
            Map<String, Object> values = new HashMap<>();
            values.put("type", type);
            values.put("content", msg);
            values.put("mobile", to);
            values.put("create_time", new Date());
            values.put("id", UUID.randomUUID());
            insert.execute(values);
        }catch (Exception e){
            log.info("数据保存失败！",e);
        }
    }
}
