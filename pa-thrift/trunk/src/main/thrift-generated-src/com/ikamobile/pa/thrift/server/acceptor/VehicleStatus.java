/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ikamobile.pa.thrift.server.acceptor;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum VehicleStatus implements org.apache.thrift.TEnum {
  /**
   *  * ENABLE-可用，DISABLE-不可用
   * *
   */
  ENABLE(1),
  DISABLE(2);

  private final int value;

  private VehicleStatus(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static VehicleStatus findByValue(int value) { 
    switch (value) {
      case 1:
        return ENABLE;
      case 2:
        return DISABLE;
      default:
        return null;
    }
  }
}
