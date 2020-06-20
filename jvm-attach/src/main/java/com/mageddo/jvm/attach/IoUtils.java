package com.mageddo.jvm.attach;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IoUtils {

  private IoUtils() {
  }

  public static Path copyFromResourcesToTempPath(String path) {
    return copyToTempPath(IoUtils.class.getResourceAsStream(path));
  }

  public static Path copyToTempPath(InputStream source) {
    try {
      final Path tmpPath = Files.createTempFile("tmp", ".tmp");
      try (
        OutputStream target = Files.newOutputStream(tmpPath)
      ) {
        copy(source, target);
      }
      return tmpPath;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static void copy(InputStream source, OutputStream target) {
    try {
      final byte[] buff = new byte[256];
      int read;
      while ((read = source.read(buff, 0, buff.length)) != -1) {
        target.write(buff, 0, read);
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
