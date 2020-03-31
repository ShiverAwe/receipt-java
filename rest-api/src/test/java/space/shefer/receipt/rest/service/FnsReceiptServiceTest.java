package space.shefer.receipt.rest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import space.shefer.receipt.fns.dto.FnsItemDto;
import space.shefer.receipt.fns.dto.FnsReceiptDto;
import space.shefer.receipt.rest.dto.ReceiptStatus;
import space.shefer.receipt.rest.entity.Item;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ItemRepository;
import space.shefer.receipt.rest.repository.ReceiptRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FnsReceiptServiceTest {

  private FnsReceiptService service;
  private ReceiptRepository receiptRepository;
  private ItemRepository itemRepository;

  @Before
  public void setUp() {
    receiptRepository = mock(ReceiptRepository.class);
    doAnswer(args -> args.getArgument(0)).when(receiptRepository).save(any());
    itemRepository = mock(ItemRepository.class);
    service = spy(new FnsReceiptService(receiptRepository, itemRepository));
  }

  @Test
  public void create() {
    FnsReceiptDto fnsReceiptDto = new FnsReceiptDto();
    fnsReceiptDto.setTotalSum(222222);
    fnsReceiptDto.setFiscalDocumentNumber(111111);
    fnsReceiptDto.setFiscalSign(333333);

    LocalDateTime dateTime = LocalDateTime.of(2020, 3, 4, 20,24, 45);
    int dateTimeEpoch = 1583353485;
    fnsReceiptDto.setDateTime(dateTimeEpoch);

    FnsItemDto fnsItemDto1 = new FnsItemDto();
    fnsItemDto1.setName("fnsItemName1");
    fnsItemDto1.setPrice(9550);
    fnsItemDto1.setQuantity(7.13);
    fnsItemDto1.setSum(68091);

    FnsItemDto fnsItemDto2 = new FnsItemDto();
    fnsItemDto2.setName("fnsItemName2");
    fnsItemDto2.setPrice(9577);
    fnsItemDto2.setQuantity(1.33);
    fnsItemDto2.setSum(12737);

    fnsReceiptDto.setItems(Arrays.asList(fnsItemDto1, fnsItemDto2));

    service.create(fnsReceiptDto);

    ArgumentCaptor<Receipt> receiptCaptor = ArgumentCaptor.forClass(Receipt.class);
    verify(receiptRepository).save(receiptCaptor.capture());
    Receipt receipt = receiptCaptor.getValue();

    assertEquals("111111", receipt.getFd());
    assertEquals(2222.22, receipt.getSum(), 1e-5);
    assertEquals("333333", receipt.getFp());
    assertEquals(dateTime, receipt.getDate());
    assertEquals(receipt.getStatus(), ReceiptStatus.LOADED);
    assertEquals("TGBOT_NALOG", receipt.getProvider());

    ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);
    verify(itemRepository, times(2)).save(itemCaptor.capture());
    List<Item> items = itemCaptor.getAllValues();

    Item item1 = items.get(0);
    assertEquals(item1.getText(), "fnsItemName1");
    assertEquals(item1.getPrice(), 95.50, 1e-5);
    assertEquals(item1.getAmount(), 7.13, 1e-5);

    Item item2 = items.get(1);
    assertEquals(item2.getText(), "fnsItemName2");
    assertEquals(item2.getPrice(), 95.77, 1e-5);
    assertEquals(item2.getAmount(), 1.33, 1e-5);
  }

}
