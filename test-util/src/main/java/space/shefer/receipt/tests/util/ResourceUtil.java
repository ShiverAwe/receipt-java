package space.shefer.receipt.tests.util;


import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ResourceUtil {
  public static String getResourceAsString(String filename, @Nullable Class<?> clazz) {
    if (clazz == null) {
      clazz = ResourceUtil.class;
    }
    try {
      return new String(Files.readAllBytes(Paths.get(clazz.getResource(filename).toURI())));
    }
    catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private ResourceUtil() {
  }
}
