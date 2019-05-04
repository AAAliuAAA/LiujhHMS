package com.smepms.workattendance.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smepms.workattendance.service.impl.dto.InsOpenData;
import com.smepms.workattendance.service.impl.dto.Room;
import net.sf.json.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author liujh
 * @since 2018/4/2
 */
public class Test {
  private final static Logger logger = LoggerFactory.getLogger(Test.class);

  private static final ObjectMapper MAPPER = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;


  public static <T> T readAs(String json, Class<T> clazz) {
    try {
      return MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @org.junit.Test
  public void testJson() throws JsonProcessingException {
    String json = "{\"dataCode\":\"ROOM\",\"deleted\":false,\"id\":56286,\"appDataId\":null,\"data\":{\"name\":\"ljh_synData\",\"dutyRole\":{\"roleName\":\"自动化运维平台管理员\",\"roleCode\":\"YWPT_GLY\",\"roleId\":1532187},\"totalArea\":5.0,\"address\":\"明发新城\",\"remark\":\"66\",\"YunXingDanWei\":{\"id\":23949,\"deleted\":false,\"builtIn\":false,\"parentId\":null,\"comName\":\"省电力公司本部\",\"comCode\":\"ShengDianLiGongSiBenBu\",\"nodeType\":null,\"depict\":null,\"dispOrder\":null,\"children\":null,\"parentComCode\":null,\"parentComName\":null},\"FangJianHao\":\"8989\",\"LouCeng\":102,\"YiYongMianJi\":2.0,\"MenJin\":\"123456\"}}";
    InsOpenData insOpenData = Test.readAs(json,InsOpenData.class);
    logger.info(insOpenData.toString());
    String content =  MAPPER.writeValueAsString((Map)insOpenData.getData());
    System.out.println(content);
    Room room = Test.readAs(content,Room.class);
    System.out.println(room);
    logger.info("总体对象数据："+room.toString());
    logger.info("同一个java文件class数据："+room.getYunXingDanWei());
  }
}
