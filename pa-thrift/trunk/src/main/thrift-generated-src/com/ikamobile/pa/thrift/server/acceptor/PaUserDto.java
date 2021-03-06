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
public class PaUserDto implements org.apache.thrift.TBase<PaUserDto, PaUserDto._Fields>, java.io.Serializable, Cloneable, Comparable<PaUserDto> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PaUserDto");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField USERNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("username", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PASSWD_FIELD_DESC = new org.apache.thrift.protocol.TField("passwd", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField SALT_FIELD_DESC = new org.apache.thrift.protocol.TField("salt", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PA_USER_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("paUserType", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PaUserDtoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PaUserDtoTupleSchemeFactory());
  }

  public String id; // required
  public String username; // required
  public String passwd; // required
  public String salt; // required
  /**
   * 
   * @see PaUserType
   */
  public PaUserType paUserType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    USERNAME((short)2, "username"),
    PASSWD((short)3, "passwd"),
    SALT((short)4, "salt"),
    /**
     * 
     * @see PaUserType
     */
    PA_USER_TYPE((short)5, "paUserType");

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
        case 2: // USERNAME
          return USERNAME;
        case 3: // PASSWD
          return PASSWD;
        case 4: // SALT
          return SALT;
        case 5: // PA_USER_TYPE
          return PA_USER_TYPE;
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
    tmpMap.put(_Fields.USERNAME, new org.apache.thrift.meta_data.FieldMetaData("username", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PASSWD, new org.apache.thrift.meta_data.FieldMetaData("passwd", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SALT, new org.apache.thrift.meta_data.FieldMetaData("salt", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PA_USER_TYPE, new org.apache.thrift.meta_data.FieldMetaData("paUserType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, PaUserType.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PaUserDto.class, metaDataMap);
  }

  public PaUserDto() {
  }

  public PaUserDto(
    String id,
    String username,
    String passwd,
    String salt,
    PaUserType paUserType)
  {
    this();
    this.id = id;
    this.username = username;
    this.passwd = passwd;
    this.salt = salt;
    this.paUserType = paUserType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PaUserDto(PaUserDto other) {
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetUsername()) {
      this.username = other.username;
    }
    if (other.isSetPasswd()) {
      this.passwd = other.passwd;
    }
    if (other.isSetSalt()) {
      this.salt = other.salt;
    }
    if (other.isSetPaUserType()) {
      this.paUserType = other.paUserType;
    }
  }

  public PaUserDto deepCopy() {
    return new PaUserDto(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.username = null;
    this.passwd = null;
    this.salt = null;
    this.paUserType = null;
  }

  public String getId() {
    return this.id;
  }

  public PaUserDto setId(String id) {
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

  public String getUsername() {
    return this.username;
  }

  public PaUserDto setUsername(String username) {
    this.username = username;
    return this;
  }

  public void unsetUsername() {
    this.username = null;
  }

  /** Returns true if field username is set (has been assigned a value) and false otherwise */
  public boolean isSetUsername() {
    return this.username != null;
  }

  public void setUsernameIsSet(boolean value) {
    if (!value) {
      this.username = null;
    }
  }

  public String getPasswd() {
    return this.passwd;
  }

  public PaUserDto setPasswd(String passwd) {
    this.passwd = passwd;
    return this;
  }

  public void unsetPasswd() {
    this.passwd = null;
  }

  /** Returns true if field passwd is set (has been assigned a value) and false otherwise */
  public boolean isSetPasswd() {
    return this.passwd != null;
  }

  public void setPasswdIsSet(boolean value) {
    if (!value) {
      this.passwd = null;
    }
  }

  public String getSalt() {
    return this.salt;
  }

  public PaUserDto setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public void unsetSalt() {
    this.salt = null;
  }

  /** Returns true if field salt is set (has been assigned a value) and false otherwise */
  public boolean isSetSalt() {
    return this.salt != null;
  }

  public void setSaltIsSet(boolean value) {
    if (!value) {
      this.salt = null;
    }
  }

  /**
   * 
   * @see PaUserType
   */
  public PaUserType getPaUserType() {
    return this.paUserType;
  }

  /**
   * 
   * @see PaUserType
   */
  public PaUserDto setPaUserType(PaUserType paUserType) {
    this.paUserType = paUserType;
    return this;
  }

  public void unsetPaUserType() {
    this.paUserType = null;
  }

  /** Returns true if field paUserType is set (has been assigned a value) and false otherwise */
  public boolean isSetPaUserType() {
    return this.paUserType != null;
  }

  public void setPaUserTypeIsSet(boolean value) {
    if (!value) {
      this.paUserType = null;
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

    case USERNAME:
      if (value == null) {
        unsetUsername();
      } else {
        setUsername((String)value);
      }
      break;

    case PASSWD:
      if (value == null) {
        unsetPasswd();
      } else {
        setPasswd((String)value);
      }
      break;

    case SALT:
      if (value == null) {
        unsetSalt();
      } else {
        setSalt((String)value);
      }
      break;

    case PA_USER_TYPE:
      if (value == null) {
        unsetPaUserType();
      } else {
        setPaUserType((PaUserType)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case USERNAME:
      return getUsername();

    case PASSWD:
      return getPasswd();

    case SALT:
      return getSalt();

    case PA_USER_TYPE:
      return getPaUserType();

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
    case USERNAME:
      return isSetUsername();
    case PASSWD:
      return isSetPasswd();
    case SALT:
      return isSetSalt();
    case PA_USER_TYPE:
      return isSetPaUserType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PaUserDto)
      return this.equals((PaUserDto)that);
    return false;
  }

  public boolean equals(PaUserDto that) {
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

    boolean this_present_username = true && this.isSetUsername();
    boolean that_present_username = true && that.isSetUsername();
    if (this_present_username || that_present_username) {
      if (!(this_present_username && that_present_username))
        return false;
      if (!this.username.equals(that.username))
        return false;
    }

    boolean this_present_passwd = true && this.isSetPasswd();
    boolean that_present_passwd = true && that.isSetPasswd();
    if (this_present_passwd || that_present_passwd) {
      if (!(this_present_passwd && that_present_passwd))
        return false;
      if (!this.passwd.equals(that.passwd))
        return false;
    }

    boolean this_present_salt = true && this.isSetSalt();
    boolean that_present_salt = true && that.isSetSalt();
    if (this_present_salt || that_present_salt) {
      if (!(this_present_salt && that_present_salt))
        return false;
      if (!this.salt.equals(that.salt))
        return false;
    }

    boolean this_present_paUserType = true && this.isSetPaUserType();
    boolean that_present_paUserType = true && that.isSetPaUserType();
    if (this_present_paUserType || that_present_paUserType) {
      if (!(this_present_paUserType && that_present_paUserType))
        return false;
      if (!this.paUserType.equals(that.paUserType))
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

    boolean present_username = true && (isSetUsername());
    list.add(present_username);
    if (present_username)
      list.add(username);

    boolean present_passwd = true && (isSetPasswd());
    list.add(present_passwd);
    if (present_passwd)
      list.add(passwd);

    boolean present_salt = true && (isSetSalt());
    list.add(present_salt);
    if (present_salt)
      list.add(salt);

    boolean present_paUserType = true && (isSetPaUserType());
    list.add(present_paUserType);
    if (present_paUserType)
      list.add(paUserType.getValue());

    return list.hashCode();
  }

  @Override
  public int compareTo(PaUserDto other) {
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
    lastComparison = Boolean.valueOf(isSetUsername()).compareTo(other.isSetUsername());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUsername()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.username, other.username);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPasswd()).compareTo(other.isSetPasswd());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPasswd()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.passwd, other.passwd);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSalt()).compareTo(other.isSetSalt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSalt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.salt, other.salt);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPaUserType()).compareTo(other.isSetPaUserType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPaUserType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paUserType, other.paUserType);
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
    StringBuilder sb = new StringBuilder("PaUserDto(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("username:");
    if (this.username == null) {
      sb.append("null");
    } else {
      sb.append(this.username);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("passwd:");
    if (this.passwd == null) {
      sb.append("null");
    } else {
      sb.append(this.passwd);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("salt:");
    if (this.salt == null) {
      sb.append("null");
    } else {
      sb.append(this.salt);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("paUserType:");
    if (this.paUserType == null) {
      sb.append("null");
    } else {
      sb.append(this.paUserType);
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

  private static class PaUserDtoStandardSchemeFactory implements SchemeFactory {
    public PaUserDtoStandardScheme getScheme() {
      return new PaUserDtoStandardScheme();
    }
  }

  private static class PaUserDtoStandardScheme extends StandardScheme<PaUserDto> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PaUserDto struct) throws org.apache.thrift.TException {
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
          case 2: // USERNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.username = iprot.readString();
              struct.setUsernameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PASSWD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.passwd = iprot.readString();
              struct.setPasswdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SALT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.salt = iprot.readString();
              struct.setSaltIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PA_USER_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.paUserType = com.ikamobile.pa.thrift.server.acceptor.PaUserType.findByValue(iprot.readI32());
              struct.setPaUserTypeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PaUserDto struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.username != null) {
        oprot.writeFieldBegin(USERNAME_FIELD_DESC);
        oprot.writeString(struct.username);
        oprot.writeFieldEnd();
      }
      if (struct.passwd != null) {
        oprot.writeFieldBegin(PASSWD_FIELD_DESC);
        oprot.writeString(struct.passwd);
        oprot.writeFieldEnd();
      }
      if (struct.salt != null) {
        oprot.writeFieldBegin(SALT_FIELD_DESC);
        oprot.writeString(struct.salt);
        oprot.writeFieldEnd();
      }
      if (struct.paUserType != null) {
        oprot.writeFieldBegin(PA_USER_TYPE_FIELD_DESC);
        oprot.writeI32(struct.paUserType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PaUserDtoTupleSchemeFactory implements SchemeFactory {
    public PaUserDtoTupleScheme getScheme() {
      return new PaUserDtoTupleScheme();
    }
  }

  private static class PaUserDtoTupleScheme extends TupleScheme<PaUserDto> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PaUserDto struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetUsername()) {
        optionals.set(1);
      }
      if (struct.isSetPasswd()) {
        optionals.set(2);
      }
      if (struct.isSetSalt()) {
        optionals.set(3);
      }
      if (struct.isSetPaUserType()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetUsername()) {
        oprot.writeString(struct.username);
      }
      if (struct.isSetPasswd()) {
        oprot.writeString(struct.passwd);
      }
      if (struct.isSetSalt()) {
        oprot.writeString(struct.salt);
      }
      if (struct.isSetPaUserType()) {
        oprot.writeI32(struct.paUserType.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PaUserDto struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.username = iprot.readString();
        struct.setUsernameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.passwd = iprot.readString();
        struct.setPasswdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.salt = iprot.readString();
        struct.setSaltIsSet(true);
      }
      if (incoming.get(4)) {
        struct.paUserType = com.ikamobile.pa.thrift.server.acceptor.PaUserType.findByValue(iprot.readI32());
        struct.setPaUserTypeIsSet(true);
      }
    }
  }

}

