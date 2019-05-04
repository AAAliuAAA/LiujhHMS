package com.smepms.common.util.handler;

/**
 * CodeEnumUtil
 *
 * @author liujh
 * @since 2018/4/25
 */
public class CodeEnumUtil {
  public static <E extends Enum<?> & CodeBaseEnum> E codeOf(Class<E> enumClass, int code) {
    E[] enumConstants = enumClass.getEnumConstants();
    for (E e : enumConstants) {
      if (e.code() == code)
        return e;
    }
    return null;
  }
}
