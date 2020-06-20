# jattach
This is a fork from [jattach][1], it basically do the same
but was designed to be a shared library, not a command.

# compiling from source

```bash
mkdir -p build &&\
  cmake -S . -B build &&\
  cd build && make
```

[1]: https://github.com/apangin/jattach
