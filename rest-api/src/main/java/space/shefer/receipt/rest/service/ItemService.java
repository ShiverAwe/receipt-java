package space.shefer.receipt.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shefer.receipt.platform.core.dto.ReportItemFilter;
import space.shefer.receipt.platform.core.entity.Item;
import space.shefer.receipt.platform.core.repository.ItemRepository;
import space.shefer.receipt.rest.converters.ReceiptItemConverter;
import space.shefer.receipt.rest.dto.ReceiptItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  private final ItemRepository itemRepository;

  public List<ReceiptItemDto> getItems(ReportItemFilter itemFilter) {
    List<Item> receipts = itemRepository.getItems(itemFilter);
    return receipts.stream().map(ReceiptItemConverter::toDto).collect(Collectors.toList());
  }

}
