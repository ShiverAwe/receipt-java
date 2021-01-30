package space.shefer.receipt.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import space.shefer.receipt.dto.ReceiptDto;
import space.shefer.receipt.dto.ReceiptItemDto;
import space.shefer.receipt.dto.ReceiptMetaDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
  public List<ReceiptDto> parse(String filePath) {
    ArrayNode receipts = readReceiptsJson(filePath);

    return StreamSupport.stream(receipts.spliterator(), false)
      .map(receipt -> (ObjectNode) receipt.get("ticket").get("document").get("receipt"))
      .map(receipt -> {
        ReceiptMetaDto receiptMetaDto = getReceiptMetaDto(receipt);
        List<ReceiptItemDto> items = getItemsOfReceiptJson(receipt);

        return ReceiptDto.builder()
          .meta(receiptMetaDto)
          .items(items)
          .build();
      })
      .collect(Collectors.toList());
  }

  @NotNull
  private static ReceiptMetaDto getReceiptMetaDto(ObjectNode receipt) {
    ReceiptMetaDto receiptMetaDto = new ReceiptMetaDto();
    receiptMetaDto.setCurrency(810);
    receiptMetaDto.setSum(receipt.get("totalSum").asDouble() / 100.0);
    receiptMetaDto.setDateTime(LocalDateTime.parse(receipt.get("dateTime").asText(), DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of("Europe/Moscow")));
    receiptMetaDto.setMerchantName(receipt.get("userInn").asText());
    return receiptMetaDto;
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
  private static ArrayNode readReceiptsJson(String filePath) {
    return (ArrayNode) MAPPER.readTree(ResourceUtils.getFile(filePath));
  }
}
