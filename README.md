# Jattach Client
Execute operations in remote running Java Virtual Machines

# Features
* Attach shared library agent
* Attach jar agent
* All other features supported by [jattach][1]

# Usage

```gradle
compile("com.mageddo.jvmattach:jattach-client:1.0.0");
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
* HotSpot JVM, OpenJ9 JVMs are not supported
* A JRE, no JDK needed

[1]: https://github.com/apangin/jattach
[2]: jvm-attach/src/test/java/com/mageddo/jvm/attach/JvmAttachTest.java
