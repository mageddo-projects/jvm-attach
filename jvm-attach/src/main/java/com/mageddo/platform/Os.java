package com.mageddo.platform;

import java.util.Optional;

public enum Os {

  LINUX(true, false, "Linux"),
  OSX(true, false, "osx"),
  WINDOWS(false, true,"windows"),
  WINDOWS_7(false, true, "windows 7"),
  ;

  private final String[] codes;
  private boolean posix;
  private boolean windows;

  Os(boolean posix, boolean windows, String... codes) {
    this.posix = posix;
    this.windows = windows;
    this.codes = codes;
  }

  public boolean isPosix() {
    return this.posix;
  }

  public boolean isWindows(){
    return this.windows;
  }

  public static Os fromOsName() {
    final String osName = System.getProperty("os.name");
    final Os os = Optional
      .ofNullable(fromOsName(osName))
      .orElseGet(() -> fromGenericOsName(osName));
    if(os == null) {
      throw new IllegalArgumentException("Operation system not found for code: " + osName);
    }
    return os;
  }

  private static Os fromGenericOsName(String osName) {
    return fromOsName(osName.split(" ")[0]);
  }

  private static Os fromOsName(String osName) {
    for (final Os os : values()) {
      for (final String osCode : os.codes) {
        if(osCode.equalsIgnoreCase(osName)){
          return os;
        }
      }
    }
    return null;
  }

}
