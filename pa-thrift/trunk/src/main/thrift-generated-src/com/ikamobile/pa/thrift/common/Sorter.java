/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ikamobile.pa.thrift.common;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * <h2>排序枚举</h2><br/>
 * 
 */
public enum Sorter implements org.apache.thrift.TEnum {
  ASC(1),
  DESC(2);

  private final int value;

  private Sorter(int value) {
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
  public static Sorter findByValue(int value) { 
    switch (value) {
      case 1:
        return ASC;
      case 2:
        return DESC;
      default:
        return null;
    }
  }
}
