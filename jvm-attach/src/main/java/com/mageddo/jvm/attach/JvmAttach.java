package com.mageddo.jvm.attach;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;

/**
 * Some possible interactions between two JVMs,
 * the target must be a HotSpot JVM distribution, like Oracle's or OpenJDK (99% of the cases).
 */
public class JvmAttach {

  public static int loadJar(InputStream sourceJar) {
    return loadJar(sourceJar, null);
  }

  public static int loadJar(InputStream sourceJar, String options) {
    final Path jarPath = IoUtils.copyToTempPath(sourceJar);
    return loadJar(String.valueOf(jarPath), options);
  }

  /**
   * Loads jar agent into current process
   */
  public static int loadJar(String path){
    return loadJar(getCurrentPid(), path);
  }

  /**
   * Loads jar agent into current process
   */
  public static int loadJar(String path, String options){
    return loadJar(getCurrentPid(), path, options);
  }

  /**
   * Injects JavaAgent jar into the target JVM process,
   * remember to configure your META-INF/MANIFEST.MF with the agent class, e.g<br/><br/>
   * <code>Agent-Class: com.mageddo.Agent</code>
   *
   * @param pid the target JVM processId
   * @param path the jar path e.g /tmp/my-jar.jar, you can optionally pass options e.g
   *             /tmp/my-jar.jar=some important information to send to the agent
   */
  public static int loadJar(int pid, String path){
    return Jattach.loadJar(pid, path);
  }

  /**
   * @see #loadJar(int, String)
   */
  public static int loadJar(int pid, String path, String options){
    return Jattach.loadJar(pid, String.format("%s%s", path, options == null ? "" : String.format("=%s", options)));
  }

  /**
   * @see #load(String, String)
   */
  public static int load(String path){
    return loadJar(getCurrentPid(), path);
  }

  /**
   * Loads shared library into current process
   */
  public static int load(String path, String options){
    return load(getCurrentPid(), path, options);
  }

  /**
   * @see #load(int, String, String)
   */
  public static int load(int pid, String path){
    return Jattach.load(pid, path, null);
  }

  /**
   * @see Jattach#load(int, String, String)
   */
  public static int load(int pid, String path, String options){
    return Jattach.load(pid, path, options);
  }

  /**
   * @see Jattach#loadLibrary(int, String)
   */
  public static int loadLibrary(int pid, String name){
    return Jattach.loadLibrary(pid, name);
  }

  /**
   * Perform some operations at the specificed JVM processId,
   * see https://github.com/mageddo/jattach/blob/55745c5/README.md
   */
  public static int attach(int pid, String... args){
    return Jattach.attach(pid, args.length, args);
  }

  /**
   * @return Current JVM processId
   */
  public static int getCurrentPid() {
    return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
  }

}
