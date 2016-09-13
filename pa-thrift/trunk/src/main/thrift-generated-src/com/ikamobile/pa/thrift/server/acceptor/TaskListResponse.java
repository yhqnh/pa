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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-8-16")
public class TaskListResponse implements org.apache.thrift.TBase<TaskListResponse, TaskListResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TaskListResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TaskListResponse");

  private static final org.apache.thrift.protocol.TField PAGER_FIELD_DESC = new org.apache.thrift.protocol.TField("pager", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TaskListResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TaskListResponseTupleSchemeFactory());
  }

  public com.ikamobile.pa.thrift.common.PagerInfoDto pager; // required
  public List<TaskListDto> data; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PAGER((short)1, "pager"),
    DATA((short)2, "data");

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
        case 1: // PAGER
          return PAGER;
        case 2: // DATA
          return DATA;
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
    tmpMap.put(_Fields.PAGER, new org.apache.thrift.meta_data.FieldMetaData("pager", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.ikamobile.pa.thrift.common.PagerInfoDto.class)));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TaskListDto.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TaskListResponse.class, metaDataMap);
  }

  public TaskListResponse() {
  }

  public TaskListResponse(
    com.ikamobile.pa.thrift.common.PagerInfoDto pager,
    List<TaskListDto> data)
  {
    this();
    this.pager = pager;
    this.data = data;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TaskListResponse(TaskListResponse other) {
    if (other.isSetPager()) {
      this.pager = new com.ikamobile.pa.thrift.common.PagerInfoDto(other.pager);
    }
    if (other.isSetData()) {
      List<TaskListDto> __this__data = new ArrayList<TaskListDto>(other.data.size());
      for (TaskListDto other_element : other.data) {
        __this__data.add(new TaskListDto(other_element));
      }
      this.data = __this__data;
    }
  }

  public TaskListResponse deepCopy() {
    return new TaskListResponse(this);
  }

  @Override
  public void clear() {
    this.pager = null;
    this.data = null;
  }

  public com.ikamobile.pa.thrift.common.PagerInfoDto getPager() {
    return this.pager;
  }

  public TaskListResponse setPager(com.ikamobile.pa.thrift.common.PagerInfoDto pager) {
    this.pager = pager;
    return this;
  }

  public void unsetPager() {
    this.pager = null;
  }

  /** Returns true if field pager is set (has been assigned a value) and false otherwise */
  public boolean isSetPager() {
    return this.pager != null;
  }

  public void setPagerIsSet(boolean value) {
    if (!value) {
      this.pager = null;
    }
  }

  public int getDataSize() {
    return (this.data == null) ? 0 : this.data.size();
  }

  public java.util.Iterator<TaskListDto> getDataIterator() {
    return (this.data == null) ? null : this.data.iterator();
  }

  public void addToData(TaskListDto elem) {
    if (this.data == null) {
      this.data = new ArrayList<TaskListDto>();
    }
    this.data.add(elem);
  }

  public List<TaskListDto> getData() {
    return this.data;
  }

  public TaskListResponse setData(List<TaskListDto> data) {
    this.data = data;
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PAGER:
      if (value == null) {
        unsetPager();
      } else {
        setPager((com.ikamobile.pa.thrift.common.PagerInfoDto)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        setData((List<TaskListDto>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PAGER:
      return getPager();

    case DATA:
      return getData();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PAGER:
      return isSetPager();
    case DATA:
      return isSetData();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TaskListResponse)
      return this.equals((TaskListResponse)that);
    return false;
  }

  public boolean equals(TaskListResponse that) {
    if (that == null)
      return false;

    boolean this_present_pager = true && this.isSetPager();
    boolean that_present_pager = true && that.isSetPager();
    if (this_present_pager || that_present_pager) {
      if (!(this_present_pager && that_present_pager))
        return false;
      if (!this.pager.equals(that.pager))
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_pager = true && (isSetPager());
    list.add(present_pager);
    if (present_pager)
      list.add(pager);

    boolean present_data = true && (isSetData());
    list.add(present_data);
    if (present_data)
      list.add(data);

    return list.hashCode();
  }

  @Override
  public int compareTo(TaskListResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPager()).compareTo(other.isSetPager());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPager()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pager, other.pager);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
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
    StringBuilder sb = new StringBuilder("TaskListResponse(");
    boolean first = true;

    sb.append("pager:");
    if (this.pager == null) {
      sb.append("null");
    } else {
      sb.append(this.pager);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("data:");
    if (this.data == null) {
      sb.append("null");
    } else {
      sb.append(this.data);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (pager != null) {
      pager.validate();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TaskListResponseStandardSchemeFactory implements SchemeFactory {
    public TaskListResponseStandardScheme getScheme() {
      return new TaskListResponseStandardScheme();
    }
  }

  private static class TaskListResponseStandardScheme extends StandardScheme<TaskListResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TaskListResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PAGER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pager = new com.ikamobile.pa.thrift.common.PagerInfoDto();
              struct.pager.read(iprot);
              struct.setPagerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.data = new ArrayList<TaskListDto>(_list8.size);
                TaskListDto _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new TaskListDto();
                  _elem9.read(iprot);
                  struct.data.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setDataIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TaskListResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.pager != null) {
        oprot.writeFieldBegin(PAGER_FIELD_DESC);
        struct.pager.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.data != null) {
        oprot.writeFieldBegin(DATA_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.data.size()));
          for (TaskListDto _iter11 : struct.data)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TaskListResponseTupleSchemeFactory implements SchemeFactory {
    public TaskListResponseTupleScheme getScheme() {
      return new TaskListResponseTupleScheme();
    }
  }

  private static class TaskListResponseTupleScheme extends TupleScheme<TaskListResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TaskListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPager()) {
        optionals.set(0);
      }
      if (struct.isSetData()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetPager()) {
        struct.pager.write(oprot);
      }
      if (struct.isSetData()) {
        {
          oprot.writeI32(struct.data.size());
          for (TaskListDto _iter12 : struct.data)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TaskListResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.pager = new com.ikamobile.pa.thrift.common.PagerInfoDto();
        struct.pager.read(iprot);
        struct.setPagerIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.data = new ArrayList<TaskListDto>(_list13.size);
          TaskListDto _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new TaskListDto();
            _elem14.read(iprot);
            struct.data.add(_elem14);
          }
        }
        struct.setDataIsSet(true);
      }
    }
  }

}

