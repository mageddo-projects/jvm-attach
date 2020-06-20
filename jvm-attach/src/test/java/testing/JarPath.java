package testing;

import java.nio.file.Path;

public class JarPath {

  private Path insideJarPath;
  private Path realPath;

  public Path getInsideJarPath() {
    return insideJarPath;
  }

  public JarPath setInsideJarPath(Path insideJarPath) {
    this.insideJarPath = insideJarPath;
    return this;
  }

  public Path getRealPath() {
    return realPath;
  }

  public JarPath setRealPath(Path realPath) {
    this.realPath = realPath;
    return this;
  }
}
