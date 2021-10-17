package space.shefer.receipt.storages.filesystem;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {


  /**
   * @throws IllegalArgumentException if file exists
   */
  @SneakyThrows
  public static File createEmptyFile(String filePath) {
    File f = new File(filePath);
    f.getParentFile().mkdirs();
    boolean isFileCreated = f.createNewFile();
    if (!isFileCreated) {
      throw new IllegalArgumentException("File " + filePath + " already exists.");
    }
    return f;
  }

  @SneakyThrows
  public static void deleteFileIfExists(String filePath) {
    File f = new File(filePath);

    if (!f.exists()) {
      return;
    }

    Files.delete(f.toPath());
  }

  public static List<String> getFileNamesOfDirectory(String directoryPath) {
    File[] files = new File(directoryPath).listFiles();

    if (files == null) {
      throw new IllegalArgumentException("Coult not retrieve files list from directory " + directoryPath);
    }

    return Stream.of(files)
      .filter(File::isFile)
      .map(File::getName)
      .collect(Collectors.toList());
  }

  @SneakyThrows
  static String readFileOrNull(String filePath) {
    Path path = Paths.get(filePath);

    if (!path.toFile().exists()) {
      return null;
    }

    byte[] encoded = Files.readAllBytes(path);

    return new String(encoded, StandardCharsets.UTF_8);
  }

}
