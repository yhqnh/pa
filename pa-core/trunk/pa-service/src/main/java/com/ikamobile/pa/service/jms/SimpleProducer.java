package com.ikamobile.pa.service.jms;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.common.exception.BusinessException;
import com.ikamobile.pa.common.jms.AbstractProducer;
import com.ikamobile.pa.common.jms.Producer;
import com.ikamobile.pa.message.common.jms.AbstractMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Data
public class SimpleProducer<T extends AbstractMessage> extends AbstractProducer<T> implements Producer<T> {

	public void send( T message) {
		try {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
			Set<ConstraintViolation<T>> validate = validator.validate(message);
			if (validate.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<T> tConstraintViolation : validate) {
					sb.append(tConstraintViolation.getPropertyPath() + tConstraintViolation.getMessage());
					sb.append("\n");
				}
				throw new BusinessException("参数验证未通过："+sb);
			} else {
				String msg = JSON.toJSONString(message);
				jmsTemplate.convertAndSend(destination, msg);
			}

		}catch (Exception e){
			log.error("消息发送失败",e);
		}
	}

}
