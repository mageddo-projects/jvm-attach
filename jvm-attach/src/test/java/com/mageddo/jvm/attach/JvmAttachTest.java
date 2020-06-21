package com.mageddo.jvm.attach;

import com.mageddo.platform.JvmArch;
import com.mageddo.platform.Platform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JvmAttachTest {

  @Test
  void mustLoadNativeAgentWithArguments(){
    // arrange
    final var nativeSimpleAgentStream = JvmAttachTest.class.getResourceAsStream(getNativeLibrary());

    // act
    final var code = JvmAttach.load(nativeSimpleAgentStream, "native agent options");

    // assert
    assertEquals(0, code);
  }

  @Test
  void mustLoadNativeAgent(){
    // arrange
    final var nativeSimpleAgentStream = JvmAttachTest.class.getResourceAsStream(getNativeLibrary());

    // act
    final var code = JvmAttach.load(nativeSimpleAgentStream);

    // assert
    assertEquals(0, code);
  }

  @Test
  void mustLoadJarAgent(){
    // arrange
    final var success = 0;
    final var simpleAgentJarStream = JvmAttachTest.class.getResourceAsStream("/simple-agent.jar");

    // act
    final var returnCode = JvmAttach.loadJar(simpleAgentJarStream);

    // assert
    assertEquals(success, returnCode);
    assertEquals("true", System.getProperty("simple.agent.ran"));
    assertEquals("null", System.getProperty("simple.agent.options"));
  }

  @Test
  void mustLoadJarAgentWithOptions(){
    // arrange
    final var success = 0;
    final var simpleAgentJarStream = JvmAttachTest.class.getResourceAsStream("/simple-agent.jar");

    // act
    final var returnCode = JvmAttach.loadJar(simpleAgentJarStream, "my options");

    // assert
    assertEquals(success, returnCode);
    assertEquals("true", System.getProperty("simple.agent.ran"));
    assertEquals("my options", System.getProperty("simple.agent.options"));
  }

  String getNativeLibrary() {
    final var os = Platform.findOs();
    final var arch = Platform.findJvmArch();
    if(os.isPosix() && arch == JvmArch.x64){
      return "/linux-x64/libsimple_agent.so";
    } else if(os.isWindows() && arch == JvmArch.x64){
      return "/windows-x64/libsimple_agent.so";
    }
    throw new UnsupportedOperationException(String.format(
      "No simple agent for testing for the platform: %s %s", os, arch
    ));
  }
}
