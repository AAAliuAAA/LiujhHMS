package com.smepms.common.util.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DbEnumTypeHandler
 *
 * @author liujh
 * @since 2018/4/25
 */
public class EnumTypeHandler<E extends Enum<?> & CodeBaseEnum> extends BaseTypeHandler<CodeBaseEnum>{

  private Class<E> clazz;

  public EnumTypeHandler(Class<E> enumType) {
    if (enumType == null)
      throw new IllegalArgumentException("Type argument cannot be null");

    this.clazz = enumType;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, CodeBaseEnum parameter, JdbcType jdbcType)
    throws SQLException {
    ps.setInt(i, parameter.code());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return CodeEnumUtil.codeOf(clazz, rs.getInt(columnName));
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return CodeEnumUtil.codeOf(clazz, rs.getInt(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return CodeEnumUtil.codeOf(clazz, cs.getInt(columnIndex));
  }
}
