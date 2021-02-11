package space.shefer.receipt.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import space.shefer.receipt.dto.ReceiptDto;
import space.shefer.receipt.dto.ReceiptMetaDto;

import javax.annotation.Nullable;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Allows to read bank operations history in CSV format which was created by Tinkoff Bank
 */
public class TinkoffReceiptsParser implements ReceiptsParser {

  private static final CsvMapper MAPPER = new CsvMapper();

  private static final CsvSchema orderLineSchema = CsvSchema.emptySchema()
    .withHeader()
    .withColumnSeparator(';');

  @SneakyThrows
  @Override
  public List<ReceiptDto> parse(File file) {
    List<Object> orderLines = MAPPER.readerFor(JsonNode.class)
      .with(orderLineSchema)
      .readValues(file)
      .readAll();

    return orderLines.stream()
      .map(it -> (ObjectNode) it)
      .map(it -> parseReceiptMeta(it))
      .filter(Objects::nonNull)
      .map(it -> ReceiptDto.builder()
        .meta(it)
        .items(new ArrayList<>())
        .tags(new HashSet<>(Arrays.asList("tinkoff")))
        .build()
      )
      .collect(Collectors.toList());
  }

  @Nullable
  private ReceiptMetaDto parseReceiptMeta(ObjectNode it) {
    String status = it.get("Статус").asText();
    if (!status.equals("OK")) {
      return null;
    }

    ReceiptMetaDto receiptMetaDto = new ReceiptMetaDto();

    double sum = -Double.parseDouble(it.get("Сумма операции").asText().replace(",", "."));
    if (sum >= 0) {
      receiptMetaDto.setSum(sum);
    }
    else {
      return null;
    }

    receiptMetaDto.setDateTime(LocalDateTime.parse(it.get("Дата операции").asText(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")).atZone(ZoneId.of("Europe/Moscow")));
    receiptMetaDto.setMerchantName(it.get("Описание").asText());

    receiptMetaDto.setCurrency(resolveCurrencyCode(it.get("Валюта операции").asText()));

    return receiptMetaDto;
  }

  private int resolveCurrencyCode(String currencyName) {
    int currency;
    if(currencyName.equals("RUB")) {
      currency = 810;
    } else{
      currency = 0;
    }
    return currency;
  }

}
