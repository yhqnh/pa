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
public class DispatcherThriftService {

  public interface Iface {

    public List<DispatcherDto> getAll() throws com.ikamobile.pa.thrift.common.TBusinessException, org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void getAll(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public List<DispatcherDto> getAll() throws com.ikamobile.pa.thrift.common.TBusinessException, org.apache.thrift.TException
    {
      send_getAll();
      return recv_getAll();
    }

    public void send_getAll() throws org.apache.thrift.TException
    {
      getAll_args args = new getAll_args();
      sendBase("getAll", args);
    }

    public List<DispatcherDto> recv_getAll() throws com.ikamobile.pa.thrift.common.TBusinessException, org.apache.thrift.TException
    {
      getAll_result result = new getAll_result();
      receiveBase(result, "getAll");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.bussinessException != null) {
        throw result.bussinessException;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "getAll failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void getAll(org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      getAll_call method_call = new getAll_call(resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class getAll_call extends org.apache.thrift.async.TAsyncMethodCall {
      public getAll_call(org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getAll", org.apache.thrift.protocol.TMessageType.CALL, 0));
        getAll_args args = new getAll_args();
        args.write(prot);
        prot.writeMessageEnd();
      }

      public List<DispatcherDto> getResult() throws com.ikamobile.pa.thrift.common.TBusinessException, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_getAll();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("getAll", new getAll());
      return processMap;
    }

    public static class getAll<I extends Iface> extends org.apache.thrift.ProcessFunction<I, getAll_args> {
      public getAll() {
        super("getAll");
      }

      public getAll_args getEmptyArgsInstance() {
        return new getAll_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public getAll_result getResult(I iface, getAll_args args) throws org.apache.thrift.TException {
        getAll_result result = new getAll_result();
        try {
          result.success = iface.getAll();
        } catch (com.ikamobile.pa.thrift.common.TBusinessException bussinessException) {
          result.bussinessException = bussinessException;
        }
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("getAll", new getAll());
      return processMap;
    }

    public static class getAll<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, getAll_args, List<DispatcherDto>> {
      public getAll() {
        super("getAll");
      }

      public getAll_args getEmptyArgsInstance() {
        return new getAll_args();
      }

      public AsyncMethodCallback<List<DispatcherDto>> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<List<DispatcherDto>>() { 
          public void onComplete(List<DispatcherDto> o) {
            getAll_result result = new getAll_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            getAll_result result = new getAll_result();
            if (e instanceof com.ikamobile.pa.thrift.common.TBusinessException) {
                        result.bussinessException = (com.ikamobile.pa.thrift.common.TBusinessException) e;
                        result.setBussinessExceptionIsSet(true);
                        msg = result;
            }
             else 
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, getAll_args args, org.apache.thrift.async.AsyncMethodCallback<List<DispatcherDto>> resultHandler) throws TException {
        iface.getAll(resultHandler);
      }
    }

  }

  public static class getAll_args implements org.apache.thrift.TBase<getAll_args, getAll_args._Fields>, java.io.Serializable, Cloneable, Comparable<getAll_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getAll_args");


    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getAll_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getAll_argsTupleSchemeFactory());
    }


    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
;

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
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getAll_args.class, metaDataMap);
    }

    public getAll_args() {
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getAll_args(getAll_args other) {
    }

    public getAll_args deepCopy() {
      return new getAll_args(this);
    }

    @Override
    public void clear() {
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getAll_args)
        return this.equals((getAll_args)that);
      return false;
    }

    public boolean equals(getAll_args that) {
      if (that == null)
        return false;

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      return list.hashCode();
    }

    @Override
    public int compareTo(getAll_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

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
      StringBuilder sb = new StringBuilder("getAll_args(");
      boolean first = true;

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

    private static class getAll_argsStandardSchemeFactory implements SchemeFactory {
      public getAll_argsStandardScheme getScheme() {
        return new getAll_argsStandardScheme();
      }
    }

    private static class getAll_argsStandardScheme extends StandardScheme<getAll_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getAll_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, getAll_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getAll_argsTupleSchemeFactory implements SchemeFactory {
      public getAll_argsTupleScheme getScheme() {
        return new getAll_argsTupleScheme();
      }
    }

    private static class getAll_argsTupleScheme extends TupleScheme<getAll_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getAll_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getAll_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
      }
    }

  }

  public static class getAll_result implements org.apache.thrift.TBase<getAll_result, getAll_result._Fields>, java.io.Serializable, Cloneable, Comparable<getAll_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getAll_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.LIST, (short)0);
    private static final org.apache.thrift.protocol.TField BUSSINESS_EXCEPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("bussinessException", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getAll_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getAll_resultTupleSchemeFactory());
    }

    public List<DispatcherDto> success; // required
    public com.ikamobile.pa.thrift.common.TBusinessException bussinessException; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      BUSSINESS_EXCEPTION((short)1, "bussinessException");

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
          case 0: // SUCCESS
            return SUCCESS;
          case 1: // BUSSINESS_EXCEPTION
            return BUSSINESS_EXCEPTION;
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
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
              new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DispatcherDto.class))));
      tmpMap.put(_Fields.BUSSINESS_EXCEPTION, new org.apache.thrift.meta_data.FieldMetaData("bussinessException", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getAll_result.class, metaDataMap);
    }

    public getAll_result() {
    }

    public getAll_result(
      List<DispatcherDto> success,
      com.ikamobile.pa.thrift.common.TBusinessException bussinessException)
    {
      this();
      this.success = success;
      this.bussinessException = bussinessException;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getAll_result(getAll_result other) {
      if (other.isSetSuccess()) {
        List<DispatcherDto> __this__success = new ArrayList<DispatcherDto>(other.success.size());
        for (DispatcherDto other_element : other.success) {
          __this__success.add(new DispatcherDto(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetBussinessException()) {
        this.bussinessException = new com.ikamobile.pa.thrift.common.TBusinessException(other.bussinessException);
      }
    }

    public getAll_result deepCopy() {
      return new getAll_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.bussinessException = null;
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<DispatcherDto> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(DispatcherDto elem) {
      if (this.success == null) {
        this.success = new ArrayList<DispatcherDto>();
      }
      this.success.add(elem);
    }

    public List<DispatcherDto> getSuccess() {
      return this.success;
    }

    public getAll_result setSuccess(List<DispatcherDto> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public com.ikamobile.pa.thrift.common.TBusinessException getBussinessException() {
      return this.bussinessException;
    }

    public getAll_result setBussinessException(com.ikamobile.pa.thrift.common.TBusinessException bussinessException) {
      this.bussinessException = bussinessException;
      return this;
    }

    public void unsetBussinessException() {
      this.bussinessException = null;
    }

    /** Returns true if field bussinessException is set (has been assigned a value) and false otherwise */
    public boolean isSetBussinessException() {
      return this.bussinessException != null;
    }

    public void setBussinessExceptionIsSet(boolean value) {
      if (!value) {
        this.bussinessException = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<DispatcherDto>)value);
        }
        break;

      case BUSSINESS_EXCEPTION:
        if (value == null) {
          unsetBussinessException();
        } else {
          setBussinessException((com.ikamobile.pa.thrift.common.TBusinessException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case BUSSINESS_EXCEPTION:
        return getBussinessException();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case BUSSINESS_EXCEPTION:
        return isSetBussinessException();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getAll_result)
        return this.equals((getAll_result)that);
      return false;
    }

    public boolean equals(getAll_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_bussinessException = true && this.isSetBussinessException();
      boolean that_present_bussinessException = true && that.isSetBussinessException();
      if (this_present_bussinessException || that_present_bussinessException) {
        if (!(this_present_bussinessException && that_present_bussinessException))
          return false;
        if (!this.bussinessException.equals(that.bussinessException))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      boolean present_bussinessException = true && (isSetBussinessException());
      list.add(present_bussinessException);
      if (present_bussinessException)
        list.add(bussinessException);

      return list.hashCode();
    }

    @Override
    public int compareTo(getAll_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetBussinessException()).compareTo(other.isSetBussinessException());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetBussinessException()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bussinessException, other.bussinessException);
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
      StringBuilder sb = new StringBuilder("getAll_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("bussinessException:");
      if (this.bussinessException == null) {
        sb.append("null");
      } else {
        sb.append(this.bussinessException);
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

    private static class getAll_resultStandardSchemeFactory implements SchemeFactory {
      public getAll_resultStandardScheme getScheme() {
        return new getAll_resultStandardScheme();
      }
    }

    private static class getAll_resultStandardScheme extends StandardScheme<getAll_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getAll_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
                {
                  org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                  struct.success = new ArrayList<DispatcherDto>(_list0.size);
                  DispatcherDto _elem1;
                  for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                  {
                    _elem1 = new DispatcherDto();
                    _elem1.read(iprot);
                    struct.success.add(_elem1);
                  }
                  iprot.readListEnd();
                }
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 1: // BUSSINESS_EXCEPTION
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.bussinessException = new com.ikamobile.pa.thrift.common.TBusinessException();
                struct.bussinessException.read(iprot);
                struct.setBussinessExceptionIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, getAll_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.success.size()));
            for (DispatcherDto _iter3 : struct.success)
            {
              _iter3.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
        if (struct.bussinessException != null) {
          oprot.writeFieldBegin(BUSSINESS_EXCEPTION_FIELD_DESC);
          struct.bussinessException.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getAll_resultTupleSchemeFactory implements SchemeFactory {
      public getAll_resultTupleScheme getScheme() {
        return new getAll_resultTupleScheme();
      }
    }

    private static class getAll_resultTupleScheme extends TupleScheme<getAll_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getAll_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetBussinessException()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetSuccess()) {
          {
            oprot.writeI32(struct.success.size());
            for (DispatcherDto _iter4 : struct.success)
            {
              _iter4.write(oprot);
            }
          }
        }
        if (struct.isSetBussinessException()) {
          struct.bussinessException.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getAll_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          {
            org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            struct.success = new ArrayList<DispatcherDto>(_list5.size);
            DispatcherDto _elem6;
            for (int _i7 = 0; _i7 < _list5.size; ++_i7)
            {
              _elem6 = new DispatcherDto();
              _elem6.read(iprot);
              struct.success.add(_elem6);
            }
          }
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.bussinessException = new com.ikamobile.pa.thrift.common.TBusinessException();
          struct.bussinessException.read(iprot);
          struct.setBussinessExceptionIsSet(true);
        }
      }
    }

  }

}
