package space.shefer.receipt.rest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import space.shefer.receipt.platform.core.dto.ReportMetaFilter;
import space.shefer.receipt.platform.core.entity.Receipt;
import space.shefer.receipt.platform.core.repository.ReceiptRepository;
import space.shefer.receipt.platform.core.util.DateUtil;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReceiptServiceTest {

  private ReceiptService service;
  private ReceiptRepository receiptRepository;

  @Before
  public void setUp() {
    receiptRepository = mock(ReceiptRepository.class);
    MerchantLogoService merchantLogoService = mock(MerchantLogoService.class);
    service = spy(new ReceiptService(receiptRepository, merchantLogoService));
  }

  @Test
  public void getReceipts() {
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
        Receipt.builder().id(1L).build(),
        Receipt.builder().id(2L).build()
      ));
    service.getReceipts(metaFilter);
    verify(receiptRepository).getReceipts(metaFilter);
  }

  @Test
  public void create() {
    ReceiptCreateDto receipt = new ReceiptCreateDto();
    receipt.setDate(DateUtil.parseReceiptDate("20190725T2313"));
    receipt.setFn("1111");
    receipt.setFd("2222");
    receipt.setFp("3333");
    receipt.setSum(100.0);
    when(receiptRepository.save(any())).thenReturn(Receipt.builder().id(400L).build());
    service.create(receipt);
    ArgumentCaptor<Receipt> receiptCaptor = ArgumentCaptor.forClass(Receipt.class);
    verify(receiptRepository).save(receiptCaptor.capture());
    Receipt actual = receiptCaptor.getValue();
    assertEquals(DateUtil.parseReceiptDate("20190725T2313"), actual.getDate());
    assertEquals("1111", actual.getFn());
    assertEquals("2222", actual.getFd());
    assertEquals("3333", actual.getFp());
    assertEquals(100.0, actual.getSum(), 1e-5);
    assertNull(actual.getProvider());
  }

  @Test
  public void correctionAddressLine() {
    String address01 = "----asdasdasdasd312314--";
    String address02 = "фы---!вфыв__---__---__--____---";
    String address03 = null;
    String address04 = "-----7----";
    String address05 = "asdad312312rfa";

    assertEquals(ReceiptService.correctionAddressLine(address01), "asdasdasdasd312314");
    assertEquals(ReceiptService.correctionAddressLine(address02), "фы---!вфыв");
    assertNull(ReceiptService.correctionAddressLine(address03));
    assertEquals(ReceiptService.correctionAddressLine(address04), "7");
    assertEquals(ReceiptService.correctionAddressLine(address05), "asdad312312rfa");
  }

}
