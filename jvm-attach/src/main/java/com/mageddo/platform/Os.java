package com.mageddo.platform;

public enum Os {

  LINUX(true, "Linux"),
  OSX(true, "osx"),
  WINDOWS("windows"),
  ;

  private final String[] codes;
  private boolean posix;

  Os(String... codes){
    this(false, codes);
  }

  Os(boolean posix, String... codes) {
    this.posix = posix;
    this.codes = codes;
  }

  public boolean isPosix() {
    return this.posix;
  }

  public static Os fromOsName() {
    final String osName = System.getProperty("os.name");
    for (final Os os : values()) {
      for (final String osCode : os.codes) {
        if(osCode.equals(osName)){
          return os;
        }
      }
    }
    throw new IllegalArgumentException("Operation system not found for code: " + osName);
  }

}
