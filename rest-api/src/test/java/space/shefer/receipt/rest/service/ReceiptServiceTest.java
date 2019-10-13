package space.shefer.receipt.rest.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.entity.Place;
import space.shefer.receipt.rest.entity.Receipt;
import space.shefer.receipt.rest.repository.ReceiptRepository;
import space.shefer.receipt.rest.service.report.ReportMetaFilter;
import space.shefer.receipt.rest.util.DateUtil;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ReceiptServiceTest {

  private ReceiptService service;
  private ReceiptRepository receiptRepository;
  private Place place = new Place(123L, "", null);

  @Before
  public void setUp() {
    receiptRepository = mock(ReceiptRepository.class);
    service = spy(new ReceiptService(receiptRepository));
  }

  @Test
  public void report() {
    ReportMetaFilter metaFilter = new ReportMetaFilter();
    metaFilter.setDateFrom(DateUtil.parseReceiptDate("20190725T2313"));
    metaFilter.setDateTo(DateUtil.parseReceiptDate("20190807T0815"));
    metaFilter.setFn("1111");
    metaFilter.setFd("2222");
    metaFilter.setFp("3333");
    metaFilter.setSumMin(100.0);
    metaFilter.setSumMax(800.0);
    when(receiptRepository.getReceipts(any()))
      .thenReturn(Arrays.asList(
        Receipt.builder().id(1L).place(place).build(),
        Receipt.builder().id(2L).place(place).build()
      ));
    service.getReceipts(metaFilter);
    verify(receiptRepository, times(1)).getReceipts(metaFilter);
  }

  @Test
  public void create() {
    ReceiptMetaDto receipt = new ReceiptMetaDto();
    receipt.setDate(DateUtil.parseReceiptDate("20190725T2313"));
    receipt.setFn("1111");
    receipt.setFd("2222");
    receipt.setFp("3333");
    receipt.setSum(100.0);
    receipt.setProvider("TAXCOM");
    when(receiptRepository.save(any())).thenReturn(Receipt.builder().id(400L).build());
    service.create(receipt);
    ArgumentCaptor<Receipt> receiptCaptor = ArgumentCaptor.forClass(Receipt.class);
    verify(receiptRepository, times(1)).save(receiptCaptor.capture());
    Receipt actual = receiptCaptor.getValue();
    Assert.assertEquals(DateUtil.parseReceiptDate("20190725T2313"), actual.getDate());
    Assert.assertEquals("1111", actual.getFn());
    Assert.assertEquals("2222", actual.getFd());
    Assert.assertEquals("3333", actual.getFp());
    Assert.assertEquals(100.0, actual.getSum(), 1e-5);
    Assert.assertEquals("TAXCOM", actual.getProvider());
  }

  @Test(expected = IllegalArgumentException.class)
  public void create_IllegalArgumentException() {
    ReceiptMetaDto receipt = new ReceiptMetaDto();
    receipt.setId(444L); // ID should be generted by database, not by user
    service.create(receipt);
  }

}
