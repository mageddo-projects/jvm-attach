package com.mageddo.jvm.attach;

import java.util.Optional;

/**
 * Represents the JVM Arch, sometimes the Os and JVM Arch are different,
 * a x86 JVM can run at a x64 OS,
 * this way a x64 shared library won't work on that JVM.
 */
public enum JvmArch {

  x64,
  x86,
  ;

  public static JvmArch fromArchModel() {
    final String arch = getSunArch();
    switch (arch){
      case "32":
        return x86;
      case "64":
        return x64;
    }
    return null;
  }

  public static JvmArch lookup() {
    final JvmArch arch = Optional
      .ofNullable(fromArchModel())
      .orElseGet(JvmArch::fromOsArch)
      ;
    if(arch == null){
      throw new IllegalStateException(String.format(
        "Unable to find JVM arch: jvmArch=%s, osArch=%s", getOsArch(), getSunArch()
      ));
    }
    return arch;
  }

  public static JvmArch fromOsArch() {
    switch (getOsArch()){
      case "amd64":
        return x64;
      default:
        return null;
    }
  }

  private static String getOsArch() {
    return System.getProperty("os.arch");
  }

  private static String getSunArch() {
    return System.getProperty("sun.arch.data.model");
  }
}
