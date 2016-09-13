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
public class DispatchParamDto implements org.apache.thrift.TBase<DispatchParamDto, DispatchParamDto._Fields>, java.io.Serializable, Cloneable, Comparable<DispatchParamDto> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DispatchParamDto");

  private static final org.apache.thrift.protocol.TField ABOARD_POSITION_FIELD_DESC = new org.apache.thrift.protocol.TField("aboardPosition", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField FLIGHT_DEP_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("flightDepTime", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField EXPECT_BOARD_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("expectBoardTime", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField VEHICLE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("vehicleCode", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ORDER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("orderId", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DispatchParamDtoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DispatchParamDtoTupleSchemeFactory());
  }

  public com.ikamobile.pa.thrift.server.acceptor.PositionDto aboardPosition; // required
  public long flightDepTime; // required
  public long expectBoardTime; // required
  public String vehicleCode; // required
  public String orderId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ABOARD_POSITION((short)1, "aboardPosition"),
    FLIGHT_DEP_TIME((short)2, "flightDepTime"),
    EXPECT_BOARD_TIME((short)3, "expectBoardTime"),
    VEHICLE_CODE((short)4, "vehicleCode"),
    ORDER_ID((short)5, "orderId");

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
        case 1: // ABOARD_POSITION
          return ABOARD_POSITION;
        case 2: // FLIGHT_DEP_TIME
          return FLIGHT_DEP_TIME;
        case 3: // EXPECT_BOARD_TIME
          return EXPECT_BOARD_TIME;
        case 4: // VEHICLE_CODE
          return VEHICLE_CODE;
        case 5: // ORDER_ID
          return ORDER_ID;
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
  private static final int __FLIGHTDEPTIME_ISSET_ID = 0;
  private static final int __EXPECTBOARDTIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ABOARD_POSITION, new org.apache.thrift.meta_data.FieldMetaData("aboardPosition", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.ikamobile.pa.thrift.server.acceptor.PositionDto.class)));
    tmpMap.put(_Fields.FLIGHT_DEP_TIME, new org.apache.thrift.meta_data.FieldMetaData("flightDepTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EXPECT_BOARD_TIME, new org.apache.thrift.meta_data.FieldMetaData("expectBoardTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VEHICLE_CODE, new org.apache.thrift.meta_data.FieldMetaData("vehicleCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORDER_ID, new org.apache.thrift.meta_data.FieldMetaData("orderId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DispatchParamDto.class, metaDataMap);
  }

  public DispatchParamDto() {
  }

  public DispatchParamDto(
    com.ikamobile.pa.thrift.server.acceptor.PositionDto aboardPosition,
    long flightDepTime,
    long expectBoardTime,
    String vehicleCode,
    String orderId)
  {
    this();
    this.aboardPosition = aboardPosition;
    this.flightDepTime = flightDepTime;
    setFlightDepTimeIsSet(true);
    this.expectBoardTime = expectBoardTime;
    setExpectBoardTimeIsSet(true);
    this.vehicleCode = vehicleCode;
    this.orderId = orderId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DispatchParamDto(DispatchParamDto other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAboardPosition()) {
      this.aboardPosition = new com.ikamobile.pa.thrift.server.acceptor.PositionDto(other.aboardPosition);
    }
    this.flightDepTime = other.flightDepTime;
    this.expectBoardTime = other.expectBoardTime;
    if (other.isSetVehicleCode()) {
      this.vehicleCode = other.vehicleCode;
    }
    if (other.isSetOrderId()) {
      this.orderId = other.orderId;
    }
  }

  public DispatchParamDto deepCopy() {
    return new DispatchParamDto(this);
  }

  @Override
  public void clear() {
    this.aboardPosition = null;
    setFlightDepTimeIsSet(false);
    this.flightDepTime = 0;
    setExpectBoardTimeIsSet(false);
    this.expectBoardTime = 0;
    this.vehicleCode = null;
    this.orderId = null;
  }

  public com.ikamobile.pa.thrift.server.acceptor.PositionDto getAboardPosition() {
    return this.aboardPosition;
  }

  public DispatchParamDto setAboardPosition(com.ikamobile.pa.thrift.server.acceptor.PositionDto aboardPosition) {
    this.aboardPosition = aboardPosition;
    return this;
  }

  public void unsetAboardPosition() {
    this.aboardPosition = null;
  }

  /** Returns true if field aboardPosition is set (has been assigned a value) and false otherwise */
  public boolean isSetAboardPosition() {
    return this.aboardPosition != null;
  }

  public void setAboardPositionIsSet(boolean value) {
    if (!value) {
      this.aboardPosition = null;
    }
  }

  public long getFlightDepTime() {
    return this.flightDepTime;
  }

  public DispatchParamDto setFlightDepTime(long flightDepTime) {
    this.flightDepTime = flightDepTime;
    setFlightDepTimeIsSet(true);
    return this;
  }

  public void unsetFlightDepTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FLIGHTDEPTIME_ISSET_ID);
  }

  /** Returns true if field flightDepTime is set (has been assigned a value) and false otherwise */
  public boolean isSetFlightDepTime() {
    return EncodingUtils.testBit(__isset_bitfield, __FLIGHTDEPTIME_ISSET_ID);
  }

  public void setFlightDepTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FLIGHTDEPTIME_ISSET_ID, value);
  }

  public long getExpectBoardTime() {
    return this.expectBoardTime;
  }

  public DispatchParamDto setExpectBoardTime(long expectBoardTime) {
    this.expectBoardTime = expectBoardTime;
    setExpectBoardTimeIsSet(true);
    return this;
  }

  public void unsetExpectBoardTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EXPECTBOARDTIME_ISSET_ID);
  }

  /** Returns true if field expectBoardTime is set (has been assigned a value) and false otherwise */
  public boolean isSetExpectBoardTime() {
    return EncodingUtils.testBit(__isset_bitfield, __EXPECTBOARDTIME_ISSET_ID);
  }

  public void setExpectBoardTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EXPECTBOARDTIME_ISSET_ID, value);
  }

  public String getVehicleCode() {
    return this.vehicleCode;
  }

  public DispatchParamDto setVehicleCode(String vehicleCode) {
    this.vehicleCode = vehicleCode;
    return this;
  }

  public void unsetVehicleCode() {
    this.vehicleCode = null;
  }

  /** Returns true if field vehicleCode is set (has been assigned a value) and false otherwise */
  public boolean isSetVehicleCode() {
    return this.vehicleCode != null;
  }

  public void setVehicleCodeIsSet(boolean value) {
    if (!value) {
      this.vehicleCode = null;
    }
  }

  public String getOrderId() {
    return this.orderId;
  }

  public DispatchParamDto setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public void unsetOrderId() {
    this.orderId = null;
  }

  /** Returns true if field orderId is set (has been assigned a value) and false otherwise */
  public boolean isSetOrderId() {
    return this.orderId != null;
  }

  public void setOrderIdIsSet(boolean value) {
    if (!value) {
      this.orderId = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ABOARD_POSITION:
      if (value == null) {
        unsetAboardPosition();
      } else {
        setAboardPosition((com.ikamobile.pa.thrift.server.acceptor.PositionDto)value);
      }
      break;

    case FLIGHT_DEP_TIME:
      if (value == null) {
        unsetFlightDepTime();
      } else {
        setFlightDepTime((Long)value);
      }
      break;

    case EXPECT_BOARD_TIME:
      if (value == null) {
        unsetExpectBoardTime();
      } else {
        setExpectBoardTime((Long)value);
      }
      break;

    case VEHICLE_CODE:
      if (value == null) {
        unsetVehicleCode();
      } else {
        setVehicleCode((String)value);
      }
      break;

    case ORDER_ID:
      if (value == null) {
        unsetOrderId();
      } else {
        setOrderId((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ABOARD_POSITION:
      return getAboardPosition();

    case FLIGHT_DEP_TIME:
      return Long.valueOf(getFlightDepTime());

    case EXPECT_BOARD_TIME:
      return Long.valueOf(getExpectBoardTime());

    case VEHICLE_CODE:
      return getVehicleCode();

    case ORDER_ID:
      return getOrderId();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ABOARD_POSITION:
      return isSetAboardPosition();
    case FLIGHT_DEP_TIME:
      return isSetFlightDepTime();
    case EXPECT_BOARD_TIME:
      return isSetExpectBoardTime();
    case VEHICLE_CODE:
      return isSetVehicleCode();
    case ORDER_ID:
      return isSetOrderId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DispatchParamDto)
      return this.equals((DispatchParamDto)that);
    return false;
  }

  public boolean equals(DispatchParamDto that) {
    if (that == null)
      return false;

    boolean this_present_aboardPosition = true && this.isSetAboardPosition();
    boolean that_present_aboardPosition = true && that.isSetAboardPosition();
    if (this_present_aboardPosition || that_present_aboardPosition) {
      if (!(this_present_aboardPosition && that_present_aboardPosition))
        return false;
      if (!this.aboardPosition.equals(that.aboardPosition))
        return false;
    }

    boolean this_present_flightDepTime = true;
    boolean that_present_flightDepTime = true;
    if (this_present_flightDepTime || that_present_flightDepTime) {
      if (!(this_present_flightDepTime && that_present_flightDepTime))
        return false;
      if (this.flightDepTime != that.flightDepTime)
        return false;
    }

    boolean this_present_expectBoardTime = true;
    boolean that_present_expectBoardTime = true;
    if (this_present_expectBoardTime || that_present_expectBoardTime) {
      if (!(this_present_expectBoardTime && that_present_expectBoardTime))
        return false;
      if (this.expectBoardTime != that.expectBoardTime)
        return false;
    }

    boolean this_present_vehicleCode = true && this.isSetVehicleCode();
    boolean that_present_vehicleCode = true && that.isSetVehicleCode();
    if (this_present_vehicleCode || that_present_vehicleCode) {
      if (!(this_present_vehicleCode && that_present_vehicleCode))
        return false;
      if (!this.vehicleCode.equals(that.vehicleCode))
        return false;
    }

    boolean this_present_orderId = true && this.isSetOrderId();
    boolean that_present_orderId = true && that.isSetOrderId();
    if (this_present_orderId || that_present_orderId) {
      if (!(this_present_orderId && that_present_orderId))
        return false;
      if (!this.orderId.equals(that.orderId))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_aboardPosition = true && (isSetAboardPosition());
    list.add(present_aboardPosition);
    if (present_aboardPosition)
      list.add(aboardPosition);

    boolean present_flightDepTime = true;
    list.add(present_flightDepTime);
    if (present_flightDepTime)
      list.add(flightDepTime);

    boolean present_expectBoardTime = true;
    list.add(present_expectBoardTime);
    if (present_expectBoardTime)
      list.add(expectBoardTime);

    boolean present_vehicleCode = true && (isSetVehicleCode());
    list.add(present_vehicleCode);
    if (present_vehicleCode)
      list.add(vehicleCode);

    boolean present_orderId = true && (isSetOrderId());
    list.add(present_orderId);
    if (present_orderId)
      list.add(orderId);

    return list.hashCode();
  }

  @Override
  public int compareTo(DispatchParamDto other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAboardPosition()).compareTo(other.isSetAboardPosition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAboardPosition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.aboardPosition, other.aboardPosition);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlightDepTime()).compareTo(other.isSetFlightDepTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlightDepTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flightDepTime, other.flightDepTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExpectBoardTime()).compareTo(other.isSetExpectBoardTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpectBoardTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expectBoardTime, other.expectBoardTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVehicleCode()).compareTo(other.isSetVehicleCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVehicleCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vehicleCode, other.vehicleCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOrderId()).compareTo(other.isSetOrderId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrderId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orderId, other.orderId);
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
    StringBuilder sb = new StringBuilder("DispatchParamDto(");
    boolean first = true;

    sb.append("aboardPosition:");
    if (this.aboardPosition == null) {
      sb.append("null");
    } else {
      sb.append(this.aboardPosition);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("flightDepTime:");
    sb.append(this.flightDepTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("expectBoardTime:");
    sb.append(this.expectBoardTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("vehicleCode:");
    if (this.vehicleCode == null) {
      sb.append("null");
    } else {
      sb.append(this.vehicleCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("orderId:");
    if (this.orderId == null) {
      sb.append("null");
    } else {
      sb.append(this.orderId);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (aboardPosition != null) {
      aboardPosition.validate();
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

  private static class DispatchParamDtoStandardSchemeFactory implements SchemeFactory {
    public DispatchParamDtoStandardScheme getScheme() {
      return new DispatchParamDtoStandardScheme();
    }
  }

  private static class DispatchParamDtoStandardScheme extends StandardScheme<DispatchParamDto> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DispatchParamDto struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ABOARD_POSITION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.aboardPosition = new com.ikamobile.pa.thrift.server.acceptor.PositionDto();
              struct.aboardPosition.read(iprot);
              struct.setAboardPositionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FLIGHT_DEP_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.flightDepTime = iprot.readI64();
              struct.setFlightDepTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXPECT_BOARD_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.expectBoardTime = iprot.readI64();
              struct.setExpectBoardTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VEHICLE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.vehicleCode = iprot.readString();
              struct.setVehicleCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ORDER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.orderId = iprot.readString();
              struct.setOrderIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DispatchParamDto struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.aboardPosition != null) {
        oprot.writeFieldBegin(ABOARD_POSITION_FIELD_DESC);
        struct.aboardPosition.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(FLIGHT_DEP_TIME_FIELD_DESC);
      oprot.writeI64(struct.flightDepTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(EXPECT_BOARD_TIME_FIELD_DESC);
      oprot.writeI64(struct.expectBoardTime);
      oprot.writeFieldEnd();
      if (struct.vehicleCode != null) {
        oprot.writeFieldBegin(VEHICLE_CODE_FIELD_DESC);
        oprot.writeString(struct.vehicleCode);
        oprot.writeFieldEnd();
      }
      if (struct.orderId != null) {
        oprot.writeFieldBegin(ORDER_ID_FIELD_DESC);
        oprot.writeString(struct.orderId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DispatchParamDtoTupleSchemeFactory implements SchemeFactory {
    public DispatchParamDtoTupleScheme getScheme() {
      return new DispatchParamDtoTupleScheme();
    }
  }

  private static class DispatchParamDtoTupleScheme extends TupleScheme<DispatchParamDto> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DispatchParamDto struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAboardPosition()) {
        optionals.set(0);
      }
      if (struct.isSetFlightDepTime()) {
        optionals.set(1);
      }
      if (struct.isSetExpectBoardTime()) {
        optionals.set(2);
      }
      if (struct.isSetVehicleCode()) {
        optionals.set(3);
      }
      if (struct.isSetOrderId()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetAboardPosition()) {
        struct.aboardPosition.write(oprot);
      }
      if (struct.isSetFlightDepTime()) {
        oprot.writeI64(struct.flightDepTime);
      }
      if (struct.isSetExpectBoardTime()) {
        oprot.writeI64(struct.expectBoardTime);
      }
      if (struct.isSetVehicleCode()) {
        oprot.writeString(struct.vehicleCode);
      }
      if (struct.isSetOrderId()) {
        oprot.writeString(struct.orderId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DispatchParamDto struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.aboardPosition = new com.ikamobile.pa.thrift.server.acceptor.PositionDto();
        struct.aboardPosition.read(iprot);
        struct.setAboardPositionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.flightDepTime = iprot.readI64();
        struct.setFlightDepTimeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.expectBoardTime = iprot.readI64();
        struct.setExpectBoardTimeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.vehicleCode = iprot.readString();
        struct.setVehicleCodeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.orderId = iprot.readString();
        struct.setOrderIdIsSet(true);
      }
    }
  }

}

