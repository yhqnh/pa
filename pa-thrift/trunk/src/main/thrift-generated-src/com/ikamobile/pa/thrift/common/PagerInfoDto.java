/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ikamobile.pa.thrift.common;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-8-1")
public class PagerInfoDto implements org.apache.thrift.TBase<PagerInfoDto, PagerInfoDto._Fields>, java.io.Serializable, Cloneable, Comparable<PagerInfoDto> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PagerInfoDto");

  private static final org.apache.thrift.protocol.TField PAGE_INDEX_FIELD_DESC = new org.apache.thrift.protocol.TField("pageIndex", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PAGE_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("pageSize", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_PAGE_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("totalPageNum", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField TOTAL_ROW_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("totalRowNum", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PagerInfoDtoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PagerInfoDtoTupleSchemeFactory());
  }

  public int pageIndex; // required
  public int pageSize; // required
  public int totalPageNum; // required
  public int totalRowNum; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PAGE_INDEX((short)1, "pageIndex"),
    PAGE_SIZE((short)2, "pageSize"),
    TOTAL_PAGE_NUM((short)3, "totalPageNum"),
    TOTAL_ROW_NUM((short)4, "totalRowNum");

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
        case 1: // PAGE_INDEX
          return PAGE_INDEX;
        case 2: // PAGE_SIZE
          return PAGE_SIZE;
        case 3: // TOTAL_PAGE_NUM
          return TOTAL_PAGE_NUM;
        case 4: // TOTAL_ROW_NUM
          return TOTAL_ROW_NUM;
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
  private static final int __PAGEINDEX_ISSET_ID = 0;
  private static final int __PAGESIZE_ISSET_ID = 1;
  private static final int __TOTALPAGENUM_ISSET_ID = 2;
  private static final int __TOTALROWNUM_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PAGE_INDEX, new org.apache.thrift.meta_data.FieldMetaData("pageIndex", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PAGE_SIZE, new org.apache.thrift.meta_data.FieldMetaData("pageSize", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOTAL_PAGE_NUM, new org.apache.thrift.meta_data.FieldMetaData("totalPageNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOTAL_ROW_NUM, new org.apache.thrift.meta_data.FieldMetaData("totalRowNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PagerInfoDto.class, metaDataMap);
  }

  public PagerInfoDto() {
  }

  public PagerInfoDto(
    int pageIndex,
    int pageSize,
    int totalPageNum,
    int totalRowNum)
  {
    this();
    this.pageIndex = pageIndex;
    setPageIndexIsSet(true);
    this.pageSize = pageSize;
    setPageSizeIsSet(true);
    this.totalPageNum = totalPageNum;
    setTotalPageNumIsSet(true);
    this.totalRowNum = totalRowNum;
    setTotalRowNumIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PagerInfoDto(PagerInfoDto other) {
    __isset_bitfield = other.__isset_bitfield;
    this.pageIndex = other.pageIndex;
    this.pageSize = other.pageSize;
    this.totalPageNum = other.totalPageNum;
    this.totalRowNum = other.totalRowNum;
  }

  public PagerInfoDto deepCopy() {
    return new PagerInfoDto(this);
  }

  @Override
  public void clear() {
    setPageIndexIsSet(false);
    this.pageIndex = 0;
    setPageSizeIsSet(false);
    this.pageSize = 0;
    setTotalPageNumIsSet(false);
    this.totalPageNum = 0;
    setTotalRowNumIsSet(false);
    this.totalRowNum = 0;
  }

  public int getPageIndex() {
    return this.pageIndex;
  }

  public PagerInfoDto setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
    setPageIndexIsSet(true);
    return this;
  }

  public void unsetPageIndex() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAGEINDEX_ISSET_ID);
  }

  /** Returns true if field pageIndex is set (has been assigned a value) and false otherwise */
  public boolean isSetPageIndex() {
    return EncodingUtils.testBit(__isset_bitfield, __PAGEINDEX_ISSET_ID);
  }

  public void setPageIndexIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAGEINDEX_ISSET_ID, value);
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public PagerInfoDto setPageSize(int pageSize) {
    this.pageSize = pageSize;
    setPageSizeIsSet(true);
    return this;
  }

  public void unsetPageSize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  /** Returns true if field pageSize is set (has been assigned a value) and false otherwise */
  public boolean isSetPageSize() {
    return EncodingUtils.testBit(__isset_bitfield, __PAGESIZE_ISSET_ID);
  }

  public void setPageSizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAGESIZE_ISSET_ID, value);
  }

  public int getTotalPageNum() {
    return this.totalPageNum;
  }

  public PagerInfoDto setTotalPageNum(int totalPageNum) {
    this.totalPageNum = totalPageNum;
    setTotalPageNumIsSet(true);
    return this;
  }

  public void unsetTotalPageNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTALPAGENUM_ISSET_ID);
  }

  /** Returns true if field totalPageNum is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalPageNum() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTALPAGENUM_ISSET_ID);
  }

  public void setTotalPageNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTALPAGENUM_ISSET_ID, value);
  }

  public int getTotalRowNum() {
    return this.totalRowNum;
  }

  public PagerInfoDto setTotalRowNum(int totalRowNum) {
    this.totalRowNum = totalRowNum;
    setTotalRowNumIsSet(true);
    return this;
  }

  public void unsetTotalRowNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTALROWNUM_ISSET_ID);
  }

  /** Returns true if field totalRowNum is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalRowNum() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTALROWNUM_ISSET_ID);
  }

  public void setTotalRowNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTALROWNUM_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PAGE_INDEX:
      if (value == null) {
        unsetPageIndex();
      } else {
        setPageIndex((Integer)value);
      }
      break;

    case PAGE_SIZE:
      if (value == null) {
        unsetPageSize();
      } else {
        setPageSize((Integer)value);
      }
      break;

    case TOTAL_PAGE_NUM:
      if (value == null) {
        unsetTotalPageNum();
      } else {
        setTotalPageNum((Integer)value);
      }
      break;

    case TOTAL_ROW_NUM:
      if (value == null) {
        unsetTotalRowNum();
      } else {
        setTotalRowNum((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PAGE_INDEX:
      return Integer.valueOf(getPageIndex());

    case PAGE_SIZE:
      return Integer.valueOf(getPageSize());

    case TOTAL_PAGE_NUM:
      return Integer.valueOf(getTotalPageNum());

    case TOTAL_ROW_NUM:
      return Integer.valueOf(getTotalRowNum());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PAGE_INDEX:
      return isSetPageIndex();
    case PAGE_SIZE:
      return isSetPageSize();
    case TOTAL_PAGE_NUM:
      return isSetTotalPageNum();
    case TOTAL_ROW_NUM:
      return isSetTotalRowNum();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PagerInfoDto)
      return this.equals((PagerInfoDto)that);
    return false;
  }

  public boolean equals(PagerInfoDto that) {
    if (that == null)
      return false;

    boolean this_present_pageIndex = true;
    boolean that_present_pageIndex = true;
    if (this_present_pageIndex || that_present_pageIndex) {
      if (!(this_present_pageIndex && that_present_pageIndex))
        return false;
      if (this.pageIndex != that.pageIndex)
        return false;
    }

    boolean this_present_pageSize = true;
    boolean that_present_pageSize = true;
    if (this_present_pageSize || that_present_pageSize) {
      if (!(this_present_pageSize && that_present_pageSize))
        return false;
      if (this.pageSize != that.pageSize)
        return false;
    }

    boolean this_present_totalPageNum = true;
    boolean that_present_totalPageNum = true;
    if (this_present_totalPageNum || that_present_totalPageNum) {
      if (!(this_present_totalPageNum && that_present_totalPageNum))
        return false;
      if (this.totalPageNum != that.totalPageNum)
        return false;
    }

    boolean this_present_totalRowNum = true;
    boolean that_present_totalRowNum = true;
    if (this_present_totalRowNum || that_present_totalRowNum) {
      if (!(this_present_totalRowNum && that_present_totalRowNum))
        return false;
      if (this.totalRowNum != that.totalRowNum)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_pageIndex = true;
    list.add(present_pageIndex);
    if (present_pageIndex)
      list.add(pageIndex);

    boolean present_pageSize = true;
    list.add(present_pageSize);
    if (present_pageSize)
      list.add(pageSize);

    boolean present_totalPageNum = true;
    list.add(present_totalPageNum);
    if (present_totalPageNum)
      list.add(totalPageNum);

    boolean present_totalRowNum = true;
    list.add(present_totalRowNum);
    if (present_totalRowNum)
      list.add(totalRowNum);

    return list.hashCode();
  }

  @Override
  public int compareTo(PagerInfoDto other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPageIndex()).compareTo(other.isSetPageIndex());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageIndex()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageIndex, other.pageIndex);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPageSize()).compareTo(other.isSetPageSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageSize, other.pageSize);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTotalPageNum()).compareTo(other.isSetTotalPageNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalPageNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalPageNum, other.totalPageNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTotalRowNum()).compareTo(other.isSetTotalRowNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalRowNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalRowNum, other.totalRowNum);
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
    StringBuilder sb = new StringBuilder("PagerInfoDto(");
    boolean first = true;

    sb.append("pageIndex:");
    sb.append(this.pageIndex);
    first = false;
    if (!first) sb.append(", ");
    sb.append("pageSize:");
    sb.append(this.pageSize);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalPageNum:");
    sb.append(this.totalPageNum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalRowNum:");
    sb.append(this.totalRowNum);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PagerInfoDtoStandardSchemeFactory implements SchemeFactory {
    public PagerInfoDtoStandardScheme getScheme() {
      return new PagerInfoDtoStandardScheme();
    }
  }

  private static class PagerInfoDtoStandardScheme extends StandardScheme<PagerInfoDto> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PagerInfoDto struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PAGE_INDEX
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageIndex = iprot.readI32();
              struct.setPageIndexIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PAGE_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageSize = iprot.readI32();
              struct.setPageSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_PAGE_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.totalPageNum = iprot.readI32();
              struct.setTotalPageNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TOTAL_ROW_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.totalRowNum = iprot.readI32();
              struct.setTotalRowNumIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PagerInfoDto struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PAGE_INDEX_FIELD_DESC);
      oprot.writeI32(struct.pageIndex);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PAGE_SIZE_FIELD_DESC);
      oprot.writeI32(struct.pageSize);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_PAGE_NUM_FIELD_DESC);
      oprot.writeI32(struct.totalPageNum);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_ROW_NUM_FIELD_DESC);
      oprot.writeI32(struct.totalRowNum);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PagerInfoDtoTupleSchemeFactory implements SchemeFactory {
    public PagerInfoDtoTupleScheme getScheme() {
      return new PagerInfoDtoTupleScheme();
    }
  }

  private static class PagerInfoDtoTupleScheme extends TupleScheme<PagerInfoDto> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PagerInfoDto struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPageIndex()) {
        optionals.set(0);
      }
      if (struct.isSetPageSize()) {
        optionals.set(1);
      }
      if (struct.isSetTotalPageNum()) {
        optionals.set(2);
      }
      if (struct.isSetTotalRowNum()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetPageIndex()) {
        oprot.writeI32(struct.pageIndex);
      }
      if (struct.isSetPageSize()) {
        oprot.writeI32(struct.pageSize);
      }
      if (struct.isSetTotalPageNum()) {
        oprot.writeI32(struct.totalPageNum);
      }
      if (struct.isSetTotalRowNum()) {
        oprot.writeI32(struct.totalRowNum);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PagerInfoDto struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.pageIndex = iprot.readI32();
        struct.setPageIndexIsSet(true);
      }
      if (incoming.get(1)) {
        struct.pageSize = iprot.readI32();
        struct.setPageSizeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.totalPageNum = iprot.readI32();
        struct.setTotalPageNumIsSet(true);
      }
      if (incoming.get(3)) {
        struct.totalRowNum = iprot.readI32();
        struct.setTotalRowNumIsSet(true);
      }
    }
  }

}
