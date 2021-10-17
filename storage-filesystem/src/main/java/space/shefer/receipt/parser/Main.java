package space.shefer.receipt.parser;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import space.shefer.receipt.dto.ReceiptDto;
import space.shefer.receipt.dto.ReceiptItemDto;
import space.shefer.receipt.dto.ReceiptMetaDto;
import space.shefer.receipt.storages.Dao;
import space.shefer.receipt.storages.filesystem.FileSystemDao;
import space.shefer.receipt.storages.filesystem.FormatType;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  private static final String ROOT_DIRECTORY = "workdir";
  private static final String SOURCE_DIRECTORY = ROOT_DIRECTORY + "/source";
  private static final String OUTPUT_DIRECTORY = ROOT_DIRECTORY + "/output";

  private static final Dao<Object> receiptDao = new FileSystemDao<>(OUTPUT_DIRECTORY, "receipts", FormatType.YAML, Object.class);

  private static final ReceiptsParser nalogReceiptsParser = new NalogReceiptsParser();
  private static final ReceiptsParser tinkoffReceiptsParser = new TinkoffReceiptsParser();

  @SneakyThrows
  public static void main(String[] args) {
    receiptDao.deleteAll();

    List<ReceiptDto> receiptsNalog = nalogReceiptsParser.parse(ResourceUtils.getFile(SOURCE_DIRECTORY + "/nalog"));
    List<ReceiptDto> receiptsTinkoff = tinkoffReceiptsParser.parse(ResourceUtils.getFile(SOURCE_DIRECTORY + "/tinkoff"));

    Set<ReceiptDto> objects = new HashSet<>();
    objects.addAll(receiptsNalog);
    objects.addAll(receiptsTinkoff);

    Map<String, List<ReceiptDto>> collect = objects.stream().collect(Collectors.groupingBy(ReceiptDto::id));

    for (Map.Entry<String, List<ReceiptDto>> id2receiptList : collect.entrySet()) {
      String id = id2receiptList.getKey();
      Set<ReceiptDto> receiptsOfId = new HashSet<>(id2receiptList.getValue());
      ReceiptDto receiptMerged = mergeReceipts(receiptsOfId);
      receiptDao.save(receiptsOfId.size() + "_" + id, receiptMerged);
    }

  }

  private static ReceiptDto mergeReceipts(Set<ReceiptDto> receiptsOfId) {
    if (receiptsOfId.size() == 1) {
      return receiptsOfId.stream().findFirst().get();
    }

    String merchantName = receiptsOfId.stream()
      .map(ReceiptDto::getMeta)
      .map(ReceiptMetaDto::getMerchantName)
      .flatMap(it -> Arrays.stream(it.split("\\|")))
      .map(String::trim)
      .distinct()
      .sorted()
      .collect(Collectors.joining(" | "));

    List<Double> sums = receiptsOfId.stream()
      .map(ReceiptDto::getMeta)
      .map(ReceiptMetaDto::getSum)
      .distinct()
      .collect(Collectors.toList());
    if (sums.size() > 1) throw new IllegalArgumentException("Unmergeable receipts: Different sums: " + sums);
    Double sum = sums.get(0);

    List<Integer> currencies = receiptsOfId.stream()
      .map(ReceiptDto::getMeta)
      .map(ReceiptMetaDto::getCurrency)
      .distinct()
      .collect(Collectors.toList());
    if (currencies.size() > 1)
      throw new IllegalArgumentException("Unmergeable receipts: Different currencies: " + sums);
    int currency = currencies.get(0);

    List<ReceiptItemDto> items = receiptsOfId.stream()
      .map(ReceiptDto::getItems)
      .flatMap(Collection::stream)
      .distinct()
      .collect(Collectors.toList());

    Set<String> tags = receiptsOfId.stream()
      .map(ReceiptDto::getTags)
      .flatMap(Collection::stream)
      .collect(Collectors.toSet());

    Map<String, String> other = receiptsOfId.stream()
      .map(ReceiptDto::getOther)
      .filter(Objects::nonNull)
      .reduce(new HashMap<>(), (a, b) -> {
        a.putAll(b);
        return a;
      });

    ReceiptDto receiptMerged = ReceiptDto.builder()
      .meta(ReceiptMetaDto.builder()
        .merchantName(merchantName)
        .sum(sum)
        .currency(currency)
        .dateTime(receiptsOfId.stream().findFirst().get().getMeta().getDateTime())
        .build())
      .items(items)
      .tags(tags)
      .other(other)
      .build();
    return receiptMerged;
  }

}
