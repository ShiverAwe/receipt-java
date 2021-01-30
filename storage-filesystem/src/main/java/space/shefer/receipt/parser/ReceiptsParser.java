package space.shefer.receipt.parser;

import space.shefer.receipt.dto.ReceiptDto;

import java.util.List;

public interface ReceiptsParser {
  List<ReceiptDto> parse(String filePath);
}
