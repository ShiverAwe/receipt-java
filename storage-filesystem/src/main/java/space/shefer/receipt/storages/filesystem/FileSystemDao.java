package space.shefer.receipt.storages.filesystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import space.shefer.receipt.storages.Dao;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static space.shefer.receipt.storages.filesystem.FileUtils.*;

@RequiredArgsConstructor
public class FileSystemDao<T> implements Dao<T> {

  private final String rootPath;
  private final String subPath;
  private final FormatType defaultFormatType;
  private final Class<T> entityType;

  private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory())
    .findAndRegisterModules()
    .enable(SerializationFeature.INDENT_OUTPUT);

  @Override
  @SneakyThrows
  public T save(String id, T entity) {
    String serialized = serialize(entity);

    deleteFileIfExists(getFilePath(id));

    File entityFile = createEmptyFile(getFilePath(id));

    try (FileWriter myWriter = new FileWriter(entityFile)) {
      myWriter.write(serialized);
      myWriter.flush();
    }

    return entity;
  }

  @Override
  public void delete(String id) {
    deleteFileIfExists(getFilePath(id));
  }

  @SneakyThrows
  @Override
  public T getById(String id) {
    String fileContents = readFileOrNull(getFilePath(id));

    if (fileContents == null) {
      throw new NoSuchElementException("No entoty for id " + id);
    }

    return objectMapper.readValue(fileContents, entityType);
  }

  @SneakyThrows
  @Nullable
  @Override
  public T getByIdOrNull(String id) {
    String fileContents = readFileOrNull(getFilePath(id));

    if (fileContents == null) {
      return null;
    }

    return objectMapper.readValue(fileContents, entityType);
  }

  @Override
  public List<T> getAll() {
    List<String> fileNamesOfDirectory = getFileNamesOfDirectory(getDirectoryPath());

    return fileNamesOfDirectory.stream()
      .map(this::getById)
      .collect(Collectors.toList());
  }

  private String serialize(T entity) throws JsonProcessingException {
    return objectMapper.writeValueAsString(entity);
  }

  private String getFilePath(String id) {
    return getDirectoryPath() + File.separator + id + "." + defaultFormatType.name().toLowerCase();
  }

  private String getDirectoryPath() {
    return rootPath + File.separator + subPath;
  }

}
