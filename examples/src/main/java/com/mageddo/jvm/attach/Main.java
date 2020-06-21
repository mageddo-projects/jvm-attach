package com.mageddo.jvm.attach;

import com.mageddo.platform.JvmArch;
import com.mageddo.platform.Os;
import com.mageddo.platform.Platform;

public class Main {
  public static void main(String[] args) {

    final int pid = args.length == 0 ? JvmAttach.getCurrentPid() : Integer.parseInt(args[0]);
    JvmAttach.load(pid, Main.class.getResourceAsStream(getSharedLibrary()));

  }

  static String getSharedLibrary() {
    final Os os = Platform.findOs();
    final JvmArch arch = Platform.findJvmArch();
    if(os.isWindows() && arch == JvmArch.x64){
      return "/windows-x64/libsimple_agent.dll";
    } else if(os.isWindows() && arch == JvmArch.x86){
      return "/windows-x86/libsimple_agent.dll";
    } else if(os.isPosix() && arch == JvmArch.x64){
      return "/linux-x64/libsimple_agent.so";
    }
    throw new UnsupportedOperationException(String.format("no agent for os: %s, arch:%s", os, arch));
  }
}
