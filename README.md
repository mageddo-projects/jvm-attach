# Jvm Attach
Jvm Attach make it easy to execute operations into 
remote running Java Virtual Machines from Java,
zero C/C++ needed.

# Features
* Attach shared library agent
* Attach jar JavaAgent
* All other features supported by [jattach][1]

# Usage

```gradle
compile("com.mageddo.jvmattach:jvm-attach:1.0.0");
```

```java
public class Main {
  public static void main(String[] args) {
    JvmAttach.loadJar(JvmAttach.getCurrentPid(), Main.class.getResourceAsStream("/simple-agent.jar"));
    // simple agent loaded: null
  }
}
```
See [unit tests][2] for more examples 


# Requirements
* HotSpot JRE 8+, OpenJ9 JVMs are not supported, no JDK needed

[1]: https://github.com/apangin/jattach
[2]: jvm-attach/src/test/java/com/mageddo/jvm/attach/JvmAttachTest.java
