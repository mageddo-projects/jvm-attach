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
compile("com.mageddo.jattach:jvm-attach:1.0.0");
```

# Requirements
* HotSpot JVM, OpenJ9 JVMs are not supported
* A JRE 8+, no JDK needed

[1]: https://github.com/apangin/jattach
