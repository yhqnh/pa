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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-8-31")
public class VehicleDto implements org.apache.thrift.TBase<VehicleDto, VehicleDto._Fields>, java.io.Serializable, Cloneable, Comparable<VehicleDto> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("VehicleDto");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("code", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("number", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField SEATS_FIELD_DESC = new org.apache.thrift.protocol.TField("seats", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField TOKEN_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("tokenCount", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField DRIVER_DTO_FIELD_DESC = new org.apache.thrift.protocol.TField("driverDto", org.apache.thrift.protocol.TType.STRUCT, (short)7);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.I32, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new VehicleDtoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new VehicleDtoTupleSchemeFactory());
  }

  /**
   * id
   */
  public String id; // required
  /**
   * 自编码
   */
  public String code; // required
  /**
   * 车牌号
   */
  public String number; // required
  /**
   * MEET-接机，TRANSPORT-送机
   * 
   * @see VehicleType
   */
  public VehicleType type; // required
  /**
   * 座位数
   */
  public int seats; // required
  /**
   * 已坐人数
   * 
   */
  public int tokenCount; // required
  /**
   * 司机
   * 
   */
  public com.ikamobile.pa.thrift.server.acceptor.DriverDto driverDto; // required
  /**
   * 状态
   * 
   * 
   * @see VehicleStatus
   */
  public VehicleStatus status; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * id
     */
    ID((short)1, "id"),
    /**
     * 自编码
     */
    CODE((short)2, "code"),
    /**
     * 车牌号
     */
    NUMBER((short)3, "number"),
    /**
     * MEET-接机，TRANSPORT-送机
     * 
     * @see VehicleType
     */
    TYPE((short)4, "type"),
    /**
     * 座位数
     */
    SEATS((short)5, "seats"),
    /**
     * 已坐人数
     * 
     */
    TOKEN_COUNT((short)6, "tokenCount"),
    /**
     * 司机
     * 
     */
    DRIVER_DTO((short)7, "driverDto"),
    /**
     * 状态
     * 
     * 
     * @see VehicleStatus
     */
    STATUS((short)8, "status");

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
        case 2: // CODE
          return CODE;
        case 3: // NUMBER
          return NUMBER;
        case 4: // TYPE
          return TYPE;
        case 5: // SEATS
          return SEATS;
        case 6: // TOKEN_COUNT
          return TOKEN_COUNT;
        case 7: // DRIVER_DTO
          return DRIVER_DTO;
        case 8: // STATUS
          return STATUS;
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
  private static final int __SEATS_ISSET_ID = 0;
  private static final int __TOKENCOUNT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CODE, new org.apache.thrift.meta_data.FieldMetaData("code", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NUMBER, new org.apache.thrift.meta_data.FieldMetaData("number", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, VehicleType.class)));
    tmpMap.put(_Fields.SEATS, new org.apache.thrift.meta_data.FieldMetaData("seats", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOKEN_COUNT, new org.apache.thrift.meta_data.FieldMetaData("tokenCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DRIVER_DTO, new org.apache.thrift.meta_data.FieldMetaData("driverDto", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.ikamobile.pa.thrift.server.acceptor.DriverDto.class)));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, VehicleStatus.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(VehicleDto.class, metaDataMap);
  }

  public VehicleDto() {
  }

  public VehicleDto(
    String id,
    String code,
    String number,
    VehicleType type,
    int seats,
    int tokenCount,
    com.ikamobile.pa.thrift.server.acceptor.DriverDto driverDto,
    VehicleStatus status)
  {
    this();
    this.id = id;
    this.code = code;
    this.number = number;
    this.type = type;
    this.seats = seats;
    setSeatsIsSet(true);
    this.tokenCount = tokenCount;
    setTokenCountIsSet(true);
    this.driverDto = driverDto;
    this.status = status;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public VehicleDto(VehicleDto other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetCode()) {
      this.code = other.code;
    }
    if (other.isSetNumber()) {
      this.number = other.number;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    this.seats = other.seats;
    this.tokenCount = other.tokenCount;
    if (other.isSetDriverDto()) {
      this.driverDto = new com.ikamobile.pa.thrift.server.acceptor.DriverDto(other.driverDto);
    }
    if (other.isSetStatus()) {
      this.status = other.status;
    }
  }

  public VehicleDto deepCopy() {
    return new VehicleDto(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.code = null;
    this.number = null;
    this.type = null;
    setSeatsIsSet(false);
    this.seats = 0;
    setTokenCountIsSet(false);
    this.tokenCount = 0;
    this.driverDto = null;
    this.status = null;
  }

  /**
   * id
   */
  public String getId() {
    return this.id;
  }

  /**
   * id
   */
  public VehicleDto setId(String id) {
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
   * 自编码
   */
  public String getCode() {
    return this.code;
  }

  /**
   * 自编码
   */
  public VehicleDto setCode(String code) {
    this.code = code;
    return this;
  }

  public void unsetCode() {
    this.code = null;
  }

  /** Returns true if field code is set (has been assigned a value) and false otherwise */
  public boolean isSetCode() {
    return this.code != null;
  }

  public void setCodeIsSet(boolean value) {
    if (!value) {
      this.code = null;
    }
  }

  /**
   * 车牌号
   */
  public String getNumber() {
    return this.number;
  }

  /**
   * 车牌号
   */
  public VehicleDto setNumber(String number) {
    this.number = number;
    return this;
  }

  public void unsetNumber() {
    this.number = null;
  }

  /** Returns true if field number is set (has been assigned a value) and false otherwise */
  public boolean isSetNumber() {
    return this.number != null;
  }

  public void setNumberIsSet(boolean value) {
    if (!value) {
      this.number = null;
    }
  }

  /**
   * MEET-接机，TRANSPORT-送机
   * 
   * @see VehicleType
   */
  public VehicleType getType() {
    return this.type;
  }

  /**
   * MEET-接机，TRANSPORT-送机
   * 
   * @see VehicleType
   */
  public VehicleDto setType(VehicleType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  /**
   * 座位数
   */
  public int getSeats() {
    return this.seats;
  }

  /**
   * 座位数
   */
  public VehicleDto setSeats(int seats) {
    this.seats = seats;
    setSeatsIsSet(true);
    return this;
  }

  public void unsetSeats() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SEATS_ISSET_ID);
  }

  /** Returns true if field seats is set (has been assigned a value) and false otherwise */
  public boolean isSetSeats() {
    return EncodingUtils.testBit(__isset_bitfield, __SEATS_ISSET_ID);
  }

  public void setSeatsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SEATS_ISSET_ID, value);
  }

  /**
   * 已坐人数
   * 
   */
  public int getTokenCount() {
    return this.tokenCount;
  }

  /**
   * 已坐人数
   * 
   */
  public VehicleDto setTokenCount(int tokenCount) {
    this.tokenCount = tokenCount;
    setTokenCountIsSet(true);
    return this;
  }

  public void unsetTokenCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOKENCOUNT_ISSET_ID);
  }

  /** Returns true if field tokenCount is set (has been assigned a value) and false otherwise */
  public boolean isSetTokenCount() {
    return EncodingUtils.testBit(__isset_bitfield, __TOKENCOUNT_ISSET_ID);
  }

  public void setTokenCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOKENCOUNT_ISSET_ID, value);
  }

  /**
   * 司机
   * 
   */
  public com.ikamobile.pa.thrift.server.acceptor.DriverDto getDriverDto() {
    return this.driverDto;
  }

  /**
   * 司机
   * 
   */
  public VehicleDto setDriverDto(com.ikamobile.pa.thrift.server.acceptor.DriverDto driverDto) {
    this.driverDto = driverDto;
    return this;
  }

  public void unsetDriverDto() {
    this.driverDto = null;
  }

  /** Returns true if field driverDto is set (has been assigned a value) and false otherwise */
  public boolean isSetDriverDto() {
    return this.driverDto != null;
  }

  public void setDriverDtoIsSet(boolean value) {
    if (!value) {
      this.driverDto = null;
    }
  }

  /**
   * 状态
   * 
   * 
   * @see VehicleStatus
   */
  public VehicleStatus getStatus() {
    return this.status;
  }

  /**
   * 状态
   * 
   * 
   * @see VehicleStatus
   */
  public VehicleDto setStatus(VehicleStatus status) {
    this.status = status;
    return this;
  }

  public void unsetStatus() {
    this.status = null;
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.status = null;
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

    case CODE:
      if (value == null) {
        unsetCode();
      } else {
        setCode((String)value);
      }
      break;

    case NUMBER:
      if (value == null) {
        unsetNumber();
      } else {
        setNumber((String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((VehicleType)value);
      }
      break;

    case SEATS:
      if (value == null) {
        unsetSeats();
      } else {
        setSeats((Integer)value);
      }
      break;

    case TOKEN_COUNT:
      if (value == null) {
        unsetTokenCount();
      } else {
        setTokenCount((Integer)value);
      }
      break;

    case DRIVER_DTO:
      if (value == null) {
        unsetDriverDto();
      } else {
        setDriverDto((com.ikamobile.pa.thrift.server.acceptor.DriverDto)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((VehicleStatus)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case CODE:
      return getCode();

    case NUMBER:
      return getNumber();

    case TYPE:
      return getType();

    case SEATS:
      return Integer.valueOf(getSeats());

    case TOKEN_COUNT:
      return Integer.valueOf(getTokenCount());

    case DRIVER_DTO:
      return getDriverDto();

    case STATUS:
      return getStatus();

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
    case CODE:
      return isSetCode();
    case NUMBER:
      return isSetNumber();
    case TYPE:
      return isSetType();
    case SEATS:
      return isSetSeats();
    case TOKEN_COUNT:
      return isSetTokenCount();
    case DRIVER_DTO:
      return isSetDriverDto();
    case STATUS:
      return isSetStatus();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof VehicleDto)
      return this.equals((VehicleDto)that);
    return false;
  }

  public boolean equals(VehicleDto that) {
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

    boolean this_present_code = true && this.isSetCode();
    boolean that_present_code = true && that.isSetCode();
    if (this_present_code || that_present_code) {
      if (!(this_present_code && that_present_code))
        return false;
      if (!this.code.equals(that.code))
        return false;
    }

    boolean this_present_number = true && this.isSetNumber();
    boolean that_present_number = true && that.isSetNumber();
    if (this_present_number || that_present_number) {
      if (!(this_present_number && that_present_number))
        return false;
      if (!this.number.equals(that.number))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_seats = true;
    boolean that_present_seats = true;
    if (this_present_seats || that_present_seats) {
      if (!(this_present_seats && that_present_seats))
        return false;
      if (this.seats != that.seats)
        return false;
    }

    boolean this_present_tokenCount = true;
    boolean that_present_tokenCount = true;
    if (this_present_tokenCount || that_present_tokenCount) {
      if (!(this_present_tokenCount && that_present_tokenCount))
        return false;
      if (this.tokenCount != that.tokenCount)
        return false;
    }

    boolean this_present_driverDto = true && this.isSetDriverDto();
    boolean that_present_driverDto = true && that.isSetDriverDto();
    if (this_present_driverDto || that_present_driverDto) {
      if (!(this_present_driverDto && that_present_driverDto))
        return false;
      if (!this.driverDto.equals(that.driverDto))
        return false;
    }

    boolean this_present_status = true && this.isSetStatus();
    boolean that_present_status = true && that.isSetStatus();
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (!this.status.equals(that.status))
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

    boolean present_code = true && (isSetCode());
    list.add(present_code);
    if (present_code)
      list.add(code);

    boolean present_number = true && (isSetNumber());
    list.add(present_number);
    if (present_number)
      list.add(number);

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_seats = true;
    list.add(present_seats);
    if (present_seats)
      list.add(seats);

    boolean present_tokenCount = true;
    list.add(present_tokenCount);
    if (present_tokenCount)
      list.add(tokenCount);

    boolean present_driverDto = true && (isSetDriverDto());
    list.add(present_driverDto);
    if (present_driverDto)
      list.add(driverDto);

    boolean present_status = true && (isSetStatus());
    list.add(present_status);
    if (present_status)
      list.add(status.getValue());

    return list.hashCode();
  }

  @Override
  public int compareTo(VehicleDto other) {
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
    lastComparison = Boolean.valueOf(isSetCode()).compareTo(other.isSetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.code, other.code);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNumber()).compareTo(other.isSetNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.number, other.number);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSeats()).compareTo(other.isSetSeats());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSeats()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.seats, other.seats);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTokenCount()).compareTo(other.isSetTokenCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTokenCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tokenCount, other.tokenCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDriverDto()).compareTo(other.isSetDriverDto());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDriverDto()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.driverDto, other.driverDto);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
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
    StringBuilder sb = new StringBuilder("VehicleDto(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("code:");
    if (this.code == null) {
      sb.append("null");
    } else {
      sb.append(this.code);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("number:");
    if (this.number == null) {
      sb.append("null");
    } else {
      sb.append(this.number);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("seats:");
    sb.append(this.seats);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tokenCount:");
    sb.append(this.tokenCount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("driverDto:");
    if (this.driverDto == null) {
      sb.append("null");
    } else {
      sb.append(this.driverDto);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("status:");
    if (this.status == null) {
      sb.append("null");
    } else {
      sb.append(this.status);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (driverDto != null) {
      driverDto.validate();
    }
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class VehicleDtoStandardSchemeFactory implements SchemeFactory {
    public VehicleDtoStandardScheme getScheme() {
      return new VehicleDtoStandardScheme();
    }
  }

  private static class VehicleDtoStandardScheme extends StandardScheme<VehicleDto> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, VehicleDto struct) throws org.apache.thrift.TException {
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
          case 2: // CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.code = iprot.readString();
              struct.setCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.number = iprot.readString();
              struct.setNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = com.ikamobile.pa.thrift.server.acceptor.VehicleType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SEATS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.seats = iprot.readI32();
              struct.setSeatsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // TOKEN_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tokenCount = iprot.readI32();
              struct.setTokenCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // DRIVER_DTO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.driverDto = new com.ikamobile.pa.thrift.server.acceptor.DriverDto();
              struct.driverDto.read(iprot);
              struct.setDriverDtoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.status = com.ikamobile.pa.thrift.server.acceptor.VehicleStatus.findByValue(iprot.readI32());
              struct.setStatusIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, VehicleDto struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.code != null) {
        oprot.writeFieldBegin(CODE_FIELD_DESC);
        oprot.writeString(struct.code);
        oprot.writeFieldEnd();
      }
      if (struct.number != null) {
        oprot.writeFieldBegin(NUMBER_FIELD_DESC);
        oprot.writeString(struct.number);
        oprot.writeFieldEnd();
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SEATS_FIELD_DESC);
      oprot.writeI32(struct.seats);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOKEN_COUNT_FIELD_DESC);
      oprot.writeI32(struct.tokenCount);
      oprot.writeFieldEnd();
      if (struct.driverDto != null) {
        oprot.writeFieldBegin(DRIVER_DTO_FIELD_DESC);
        struct.driverDto.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.status != null) {
        oprot.writeFieldBegin(STATUS_FIELD_DESC);
        oprot.writeI32(struct.status.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VehicleDtoTupleSchemeFactory implements SchemeFactory {
    public VehicleDtoTupleScheme getScheme() {
      return new VehicleDtoTupleScheme();
    }
  }

  private static class VehicleDtoTupleScheme extends TupleScheme<VehicleDto> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, VehicleDto struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetCode()) {
        optionals.set(1);
      }
      if (struct.isSetNumber()) {
        optionals.set(2);
      }
      if (struct.isSetType()) {
        optionals.set(3);
      }
      if (struct.isSetSeats()) {
        optionals.set(4);
      }
      if (struct.isSetTokenCount()) {
        optionals.set(5);
      }
      if (struct.isSetDriverDto()) {
        optionals.set(6);
      }
      if (struct.isSetStatus()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetCode()) {
        oprot.writeString(struct.code);
      }
      if (struct.isSetNumber()) {
        oprot.writeString(struct.number);
      }
      if (struct.isSetType()) {
        oprot.writeI32(struct.type.getValue());
      }
      if (struct.isSetSeats()) {
        oprot.writeI32(struct.seats);
      }
      if (struct.isSetTokenCount()) {
        oprot.writeI32(struct.tokenCount);
      }
      if (struct.isSetDriverDto()) {
        struct.driverDto.write(oprot);
      }
      if (struct.isSetStatus()) {
        oprot.writeI32(struct.status.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, VehicleDto struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.code = iprot.readString();
        struct.setCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.number = iprot.readString();
        struct.setNumberIsSet(true);
      }
      if (incoming.get(3)) {
        struct.type = com.ikamobile.pa.thrift.server.acceptor.VehicleType.findByValue(iprot.readI32());
        struct.setTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.seats = iprot.readI32();
        struct.setSeatsIsSet(true);
      }
      if (incoming.get(5)) {
        struct.tokenCount = iprot.readI32();
        struct.setTokenCountIsSet(true);
      }
      if (incoming.get(6)) {
        struct.driverDto = new com.ikamobile.pa.thrift.server.acceptor.DriverDto();
        struct.driverDto.read(iprot);
        struct.setDriverDtoIsSet(true);
      }
      if (incoming.get(7)) {
        struct.status = com.ikamobile.pa.thrift.server.acceptor.VehicleStatus.findByValue(iprot.readI32());
        struct.setStatusIsSet(true);
      }
    }
  }

}
