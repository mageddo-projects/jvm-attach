package testing;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarMaker {

  public static void write(
    OutputStream jarTargetPath,
    JarPath[] jarPaths,
    String comment,
    Manifest manifest
  ){
    try {
      write0(jarTargetPath, jarPaths, comment, manifest);
    } catch (IOException e){
      throw new UncheckedIOException(e);
    }
  }

  private static void write0(
    OutputStream jarTargetPath,
    JarPath[] jarPaths,
    String comment,
    Manifest manifest
  ) throws IOException {
    final JarOutputStream jos = new JarOutputStream(jarTargetPath, manifest);
    BufferedOutputStream bos = new BufferedOutputStream(jos);
    jos.setComment(comment);
    for (final JarPath jarPath : jarPaths) {
      jos.putNextEntry(new JarEntry(jarPath.getInsideJarPath().toString()));
      try(BufferedReader reader = Files.newBufferedReader(jarPath.getRealPath())){
        copy(reader, bos);
      }
    }
    bos.close();
  }

  private static void copy(BufferedReader source, OutputStream target) {
    try {
      int c;
      while ((c = source.read()) != -1) {
        target.write(c);
      }
    } catch (IOException e){
      throw new UncheckedIOException(e);
    }
  }
}
