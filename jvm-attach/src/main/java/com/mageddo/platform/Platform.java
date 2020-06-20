package com.mageddo.platform;

public class Platform {

  public static Os findOs(){
    return Os.fromOsName();
  }

  public static JvmArch findJvmArch(){
    return JvmArch.lookup();
  }
}
