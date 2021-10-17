package space.shefer.receipt.parser;

import space.shefer.receipt.dto.ReceiptDto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ReceiptsParser {

  List<ReceiptDto> parseFile(File file);

  /**
   * Recursively reads all files of directory if directory passed or reads yhe file if file passed and then parses and merges results into list of receipts.
   *
   * @param file file or directory
   * @return list of receipts
   * @throws IllegalArgumentException if neither file nor directory passed.
   */
  default List<ReceiptDto> parse(File file) {
    if (file.isFile()) {
      return parseFile(file);
    }

    if (file.isDirectory()) {
      List<ReceiptDto> result = new ArrayList<>();
      for (File listFile : Objects.requireNonNull(file.listFiles())) {
        result = merge(result, parse(listFile));
      }
      return result;
    }

    throw new IllegalArgumentException("Path is neither file not directory: " + file.toString());
  }

  /**
   * Merge two lists of the same provider receipts.
   * Mainly used to concatenate two lists omitting the duplicates.
   * The incoming and resulting lists could be unsorted.
   */
  default List<ReceiptDto> merge(List<ReceiptDto> list1, List<ReceiptDto> list2) {
    ArrayList<ReceiptDto> result = new ArrayList<>(list1);
    result.addAll(list2);
    return result;
  }

  ;
}
