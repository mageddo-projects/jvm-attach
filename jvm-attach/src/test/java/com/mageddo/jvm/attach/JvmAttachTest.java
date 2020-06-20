package com.mageddo.jvm.attach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JvmAttachTest {

  @Test
  void mustLoadJarAgent(){
    // arrange
    final var success = 0;
    final var simpleAgentJarStream = JvmAttachTest.class.getResourceAsStream("/simple-agent.jar");

    // act
    final var returnCode = JvmAttach.loadJar(simpleAgentJarStream);

    // assert
    assertEquals(success, returnCode);
  }

}
