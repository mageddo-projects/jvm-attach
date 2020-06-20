package com.mageddo.jvm.attach;

import java.nio.file.Path;

import static com.mageddo.jvm.attach.IoUtils.copyFromResourcesToTempPath;

class Jattach {

  static {
    loadJattach();
  }

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


  private static void loadJattach() {
    final Path libraryPath = copyFromResourcesToTempPath(getJattachSharedLibraryPath());
    System.load(String.valueOf(libraryPath));
  }

  private static String getJattachSharedLibraryPath() {
    final Os os = Platform.findOs();
    final JvmArch arch = Platform.findJvmArch();
    if(os.isPosix() && arch == JvmArch.x64){
      return "/libraries/linux-x64/libjattach.so";
    }
    throw new UnsupportedOperationException(String.format(
      "Jattach shared library not found to os=%s, jvm arch=%s",
      os,
      arch
    ));
  }
}
