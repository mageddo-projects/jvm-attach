package com.mageddo.jattach;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("before");
    System.load("/home/typer/dev/projects/ram-spider/jattach/build/libjattach.so");
//    final var returnCode = Jattach.loadJar(
//        getCurrentPid(),
//        "/home/typer/dev/projects/java-examples/runtime-jar-loading/simple-example/hello-world-library/build/libs"
//            + "/hello-world-library.jar=some arg to test"
//    );
//    final var returnCode = Jattach.load(getCurrentPid(), "/home/typer/dev/projects/ram-spider/jattach/build/libsimple_agent.so");
//    final var returnCode = Jattach.load(
//        getCurrentPid(),
//        "/home/typer/dev/projects/ram-spider/jattach/build/libjar_load_agent.so",
//        "awesome arg"
//    );
//
//    Jattach.attach(
//        JattachClient.getCurrentPid(),
//        3,
//        "load",
//        "/home/typer/dev/projects/ram-spider/jattach/build/libsimple_agent.so",
//        "true"
//    );
//    System.out.println("returned code: " + returnCode);
  }
}
