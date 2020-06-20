package com.mageddo.jattach;

class Jattach {

  public static native int loadJar(int pid, String path);

  /**
   * Loads shared library into the specified JVM process id.
   * @see System#load(String)
   */
  public static native int load(int pid, String path, String options);

  /**
   * Loads shared library into the specified JVM process id.
   * @see System#loadLibrary(String)
   */
  public static native int loadLibrary(int pid, String name);

  /**
   * Perform some operations at the specificed JVM processId,
   * see https://github.com/mageddo/jattach/blob/55745c5/README.md
   */
  public static native int attach(int pid, int argc, String... args);
}
