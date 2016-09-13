/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ikamobile.pa.thrift.server.acceptor;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-7-21")
public class DispatcherDto implements org.apache.thrift.TBase<DispatcherDto, DispatcherDto._Fields>, java.io.Serializable, Cloneable, Comparable<DispatcherDto> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DispatcherDto");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField LOGIN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("loginName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PHONE_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("phoneNumber", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PASSWORD_FIELD_DESC = new org.apache.thrift.protocol.TField("password", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DispatcherDtoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DispatcherDtoTupleSchemeFactory());
  }

  /**
   * uuid id
   */
  public String id; // required
  /**
   * 用户名
   */
  public String loginName; // required
  /**
   * 手机号
   */
  public String phoneNumber; // required
  /**
   * 密码
   */
  public String password; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * uuid id
     */
    ID((short)1, "id"),
    /**
     * 用户名
     */
    LOGIN_NAME((short)2, "loginName"),
    /**
     * 手机号
     */
    PHONE_NUMBER((short)3, "phoneNumber"),
    /**
     * 密码
     */
    PASSWORD((short)4, "password");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // LOGIN_NAME
          return LOGIN_NAME;
        case 3: // PHONE_NUMBER
          return PHONE_NUMBER;
        case 4: // PASSWORD
          return PASSWORD;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOGIN_NAME, new org.apache.thrift.meta_data.FieldMetaData("loginName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PHONE_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("phoneNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PASSWORD, new org.apache.thrift.meta_data.FieldMetaData("password", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DispatcherDto.class, metaDataMap);
  }

  public DispatcherDto() {
  }

  public DispatcherDto(
    String id,
    String loginName,
    String phoneNumber,
    String password)
  {
    this();
    this.id = id;
    this.loginName = loginName;
    this.phoneNumber = phoneNumber;
    this.password = password;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DispatcherDto(DispatcherDto other) {
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetLoginName()) {
      this.loginName = other.loginName;
    }
    if (other.isSetPhoneNumber()) {
      this.phoneNumber = other.phoneNumber;
    }
    if (other.isSetPassword()) {
      this.password = other.password;
    }
  }

  public DispatcherDto deepCopy() {
    return new DispatcherDto(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.loginName = null;
    this.phoneNumber = null;
    this.password = null;
  }

  /**
   * uuid id
   */
  public String getId() {
    return this.id;
  }

  /**
   * uuid id
   */
  public DispatcherDto setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  /**
   * 用户名
   */
  public String getLoginName() {
    return this.loginName;
  }

  /**
   * 用户名
   */
  public DispatcherDto setLoginName(String loginName) {
    this.loginName = loginName;
    return this;
  }

  public void unsetLoginName() {
    this.loginName = null;
  }

  /** Returns true if field loginName is set (has been assigned a value) and false otherwise */
  public boolean isSetLoginName() {
    return this.loginName != null;
  }

  public void setLoginNameIsSet(boolean value) {
    if (!value) {
      this.loginName = null;
    }
  }

  /**
   * 手机号
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * 手机号
   */
  public DispatcherDto setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public void unsetPhoneNumber() {
    this.phoneNumber = null;
  }

  /** Returns true if field phoneNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetPhoneNumber() {
    return this.phoneNumber != null;
  }

  public void setPhoneNumberIsSet(boolean value) {
    if (!value) {
      this.phoneNumber = null;
    }
  }

  /**
   * 密码
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * 密码
   */
  public DispatcherDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public void unsetPassword() {
    this.password = null;
  }

  /** Returns true if field password is set (has been assigned a value) and false otherwise */
  public boolean isSetPassword() {
    return this.password != null;
  }

  public void setPasswordIsSet(boolean value) {
    if (!value) {
      this.password = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case LOGIN_NAME:
      if (value == null) {
        unsetLoginName();
      } else {
        setLoginName((String)value);
      }
      break;

    case PHONE_NUMBER:
      if (value == null) {
        unsetPhoneNumber();
      } else {
        setPhoneNumber((String)value);
      }
      break;

    case PASSWORD:
      if (value == null) {
        unsetPassword();
      } else {
        setPassword((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case LOGIN_NAME:
      return getLoginName();

    case PHONE_NUMBER:
      return getPhoneNumber();

    case PASSWORD:
      return getPassword();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case LOGIN_NAME:
      return isSetLoginName();
    case PHONE_NUMBER:
      return isSetPhoneNumber();
    case PASSWORD:
      return isSetPassword();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DispatcherDto)
      return this.equals((DispatcherDto)that);
    return false;
  }

  public boolean equals(DispatcherDto that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_loginName = true && this.isSetLoginName();
    boolean that_present_loginName = true && that.isSetLoginName();
    if (this_present_loginName || that_present_loginName) {
      if (!(this_present_loginName && that_present_loginName))
        return false;
      if (!this.loginName.equals(that.loginName))
        return false;
    }

    boolean this_present_phoneNumber = true && this.isSetPhoneNumber();
    boolean that_present_phoneNumber = true && that.isSetPhoneNumber();
    if (this_present_phoneNumber || that_present_phoneNumber) {
      if (!(this_present_phoneNumber && that_present_phoneNumber))
        return false;
      if (!this.phoneNumber.equals(that.phoneNumber))
        return false;
    }

    boolean this_present_password = true && this.isSetPassword();
    boolean that_present_password = true && that.isSetPassword();
    if (this_present_password || that_present_password) {
      if (!(this_present_password && that_present_password))
        return false;
      if (!this.password.equals(that.password))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_loginName = true && (isSetLoginName());
    list.add(present_loginName);
    if (present_loginName)
      list.add(loginName);

    boolean present_phoneNumber = true && (isSetPhoneNumber());
    list.add(present_phoneNumber);
    if (present_phoneNumber)
      list.add(phoneNumber);

    boolean present_password = true && (isSetPassword());
    list.add(present_password);
    if (present_password)
      list.add(password);

    return list.hashCode();
  }

  @Override
  public int compareTo(DispatcherDto other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLoginName()).compareTo(other.isSetLoginName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoginName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loginName, other.loginName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPhoneNumber()).compareTo(other.isSetPhoneNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPhoneNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.phoneNumber, other.phoneNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPassword()).compareTo(other.isSetPassword());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPassword()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.password, other.password);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DispatcherDto(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("loginName:");
    if (this.loginName == null) {
      sb.append("null");
    } else {
      sb.append(this.loginName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("phoneNumber:");
    if (this.phoneNumber == null) {
      sb.append("null");
    } else {
      sb.append(this.phoneNumber);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("password:");
    if (this.password == null) {
      sb.append("null");
    } else {
      sb.append(this.password);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DispatcherDtoStandardSchemeFactory implements SchemeFactory {
    public DispatcherDtoStandardScheme getScheme() {
      return new DispatcherDtoStandardScheme();
    }
  }

  private static class DispatcherDtoStandardScheme extends StandardScheme<DispatcherDto> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DispatcherDto struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LOGIN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.loginName = iprot.readString();
              struct.setLoginNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PHONE_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.phoneNumber = iprot.readString();
              struct.setPhoneNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PASSWORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.password = iprot.readString();
              struct.setPasswordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DispatcherDto struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.loginName != null) {
        oprot.writeFieldBegin(LOGIN_NAME_FIELD_DESC);
        oprot.writeString(struct.loginName);
        oprot.writeFieldEnd();
      }
      if (struct.phoneNumber != null) {
        oprot.writeFieldBegin(PHONE_NUMBER_FIELD_DESC);
        oprot.writeString(struct.phoneNumber);
        oprot.writeFieldEnd();
      }
      if (struct.password != null) {
        oprot.writeFieldBegin(PASSWORD_FIELD_DESC);
        oprot.writeString(struct.password);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DispatcherDtoTupleSchemeFactory implements SchemeFactory {
    public DispatcherDtoTupleScheme getScheme() {
      return new DispatcherDtoTupleScheme();
    }
  }

  private static class DispatcherDtoTupleScheme extends TupleScheme<DispatcherDto> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DispatcherDto struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetLoginName()) {
        optionals.set(1);
      }
      if (struct.isSetPhoneNumber()) {
        optionals.set(2);
      }
      if (struct.isSetPassword()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetLoginName()) {
        oprot.writeString(struct.loginName);
      }
      if (struct.isSetPhoneNumber()) {
        oprot.writeString(struct.phoneNumber);
      }
      if (struct.isSetPassword()) {
        oprot.writeString(struct.password);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DispatcherDto struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.loginName = iprot.readString();
        struct.setLoginNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.phoneNumber = iprot.readString();
        struct.setPhoneNumberIsSet(true);
      }
      if (incoming.get(3)) {
        struct.password = iprot.readString();
        struct.setPasswordIsSet(true);
      }
    }
  }

}

