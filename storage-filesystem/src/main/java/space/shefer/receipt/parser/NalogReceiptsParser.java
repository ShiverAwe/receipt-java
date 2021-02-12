package space.shefer.receipt.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import space.shefer.receipt.dto.ReceiptDto;
import space.shefer.receipt.dto.ReceiptItemDto;
import space.shefer.receipt.dto.ReceiptMetaDto;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Allows to read operations history from JSON file which was created by "Proverka Checka" mobile app.
 *
 * User of "Proverka checka" mobile application could sent the operations history to email. This file format is parsed by this class.
 */
public class NalogReceiptsParser implements ReceiptsParser {

  private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory())
    .findAndRegisterModules();

  @SneakyThrows
  @Override
  public List<ReceiptDto> parseFile(File file) {
    ArrayNode receipts = readReceiptsJson(file);

    return StreamSupport.stream(receipts.spliterator(), false)
      .map(receipt -> (ObjectNode) receipt.get("ticket").get("document").get("receipt"))
      .map(receipt -> {
        ReceiptMetaDto receiptMetaDto = getReceiptMetaDto(receipt);
        List<ReceiptItemDto> items = getItemsOfReceiptJson(receipt);

        return ReceiptDto.builder()
          .meta(receiptMetaDto)
          .items(items)
          .tags(new HashSet<>(Arrays.asList("nalog")))
          .build();
      })
      .collect(Collectors.toList());
  }

  @NotNull
  private static ReceiptMetaDto getReceiptMetaDto(ObjectNode receipt) {
    return ReceiptMetaDto.builder()
      .currency(810)
      .sum(receipt.get("totalSum").asDouble() / 100.0)
      .dateTime(LocalDateTime.parse(receipt.get("dateTime").asText(), DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of("Europe/Moscow")))
      .merchantName(receipt.get("userInn").asText())
      .build();
  }

  @SneakyThrows
  private static List<ReceiptItemDto> getItemsOfReceiptJson(ObjectNode receipt) {
    ArrayNode items = (ArrayNode) receipt.get("items");

    return StreamSupport.stream(items.spliterator(), false)
      .map(item -> getReceiptItemDto((ObjectNode) item))
      .collect(Collectors.toList());
  }

  private static ReceiptItemDto getReceiptItemDto(ObjectNode item) {
    ReceiptItemDto receiptItemDto = new ReceiptItemDto();
    receiptItemDto.setName(item.get("name").asText());
    receiptItemDto.setPrice(item.get("price").asDouble() / 100.0);
    receiptItemDto.setAmount(item.get("quantity").asDouble());
    receiptItemDto.setSum(item.get("sum").asDouble() / 100.0);
    return receiptItemDto;
  }

  @SneakyThrows
  private static ArrayNode readReceiptsJson(File file) {
    return (ArrayNode) MAPPER.readTree(file);
  }
}
