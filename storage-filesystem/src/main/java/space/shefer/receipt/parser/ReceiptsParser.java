package space.shefer.receipt.parser;

import space.shefer.receipt.dto.ReceiptDto;

import java.io.File;
import java.util.List;

public interface ReceiptsParser {
  List<ReceiptDto> parse(File file);
}
